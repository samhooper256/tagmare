package mechanics;

import java.util.*;

import base.VisualManager;
import mechanics.actions.*;
import mechanics.cards.*;
import mechanics.cards.singed.Guilt;
import mechanics.effects.*;
import mechanics.enemies.*;
import mechanics.input.CardInquiry;
import utils.RNG;

//TODO should Concentration apply to Free Time?
//TODO Guilt card needs to actually work.
public final class Combat {

	/** The default number of cards drawn per turn. */
	public static final int DEFAULT_DRAW = 5, DEFAULT_ENERGY = 3;
	
	private final ActionStack stack;
	private final Set<Card> cardsInPlay;
	private final DrawPile drawPile;
	private final DiscardPile discardPile;
	private final Hand hand;
	private final Energy energy;
	/** In order from left to right as they appear to the player. */
	private final List<Enemy> enemies;
	
	private CombatState state;
	private volatile boolean running;
	private int turn, cardsPlayedThisTurn;
	private Action mostRecentlyExecuted;
	private CardInquiry cardInquiry;
	
	public Combat() {
		stack = new ActionStack();
		cardsInPlay = new HashSet<>();
		drawPile = new DrawPile();
		discardPile = new DiscardPile();
		hand = new Hand();
		energy = new Energy();
		enemies = new ArrayList<>();
		Collections.<Enemy>addAll(enemies, new VocabQuiz(), new APESProgressCheck(), new CalculusPracticeQuiz());
		state = CombatState.PREP;
		turn = 0;
		cardsPlayedThisTurn = 0;
		running = false;
		mostRecentlyExecuted = null;
	}
	
	/** The card won't actually be played until this {@link Combat} {@link #resume() resumes}.
	 * @throws IllegalStateException if the given {@link Card} is not in the {@link Hand}.
	 * @throws IllegalStateException if the given {@link Enemy} is non-{@code null} and is not an enemy in this combat.
	 * @throws IllegalStateException if {@link #isRunning()}.
	 * @throws IllegalStateException if {@code 
	 * (card.isTargetted() && target == null || !card.isTargetted() && target != null)}*/
	public void stackPlayCardFromHand(Card card, Enemy target) {
		if(isRunning())
			throw new IllegalStateException("Running");
		if(!hand().contains(card))
			throw new IllegalStateException(String.format("Card is not in hand: %s", card));
		if(target != null && !enemies().contains(target))
			throw new IllegalStateException(String.format("Enemy is not in this combat: %s", target));
		if(card.isTargetted() && target == null || !card.isTargetted() && target != null)
			throw new IllegalStateException("Targetting does not match up");
		stack().push(new PutCardInPlay(card, Hub.player(), target));
	}
	
	/** A target will be randomly generated. */
	public Action getPlayBypassedCardAction(Card card) {
		//First, find a target.
		Enemy target = null;
		target_finder:
		if(card.isTargetted()) {
			List<Enemy> perm = RNG.perm(enemies);
			//Pass 1: Find a target that would be legal for this card.
			for(Enemy e : perm) {
				if(card.isLegal(e)) {
					target = e;
					break target_finder;
				}
			}
			target = perm.get(0);
		}
		return new PutBypassedCardInPlay(card, null, target);
	}
	
	
	/** Must call {@link #resume()} after calling this method for the player's first turn to start. */
	public void startWithoutResuming() {
		if(started())
			throw new IllegalStateException(String.format("Already started (turn=%d)", turn));
		drawPile.addAllToTop(Hub.deck().shuffledCopyOfCards());
		stackStartPlayerTurn(); //increments turn; does not call resume().
	}
	
	public void resume() {
		if(!started())
			throw new IllegalStateException("Not started");
		running = true;
		while(!stack().isEmpty()) {
			Action top = stack().pop();
			if(top.canExecute()) {
				mostRecentlyExecuted = top;
				VisualManager.get().executeAction(mostRecentlyExecuted);
				addClearsIfEnemyKilled(mostRecentlyExecuted);
				if(mostRecentlyExecuted instanceof CardAccepting)
					clearInquiry();
				if(paused())
					return;
			}
		}
		if(state == CombatState.PLAYER_TO_ENEMY) {
			stack().push(new StartEnemyTurn());
			resume();
		}
		if(state == CombatState.ENEMY_TURN) { //enemies have nothing else to do - start the player's next turn.
			stackStartPlayerTurn();
			resume();
		}
		else {
			running = false;
		}
	}
	
	/** Adds a {@link ClearEnemy} to the top of the {@link #stack()} for every {@link Enemy} killed by the given
	 * {@link Action}. */
	private void addClearsIfEnemyKilled(Action top) {
		if(top == null || top instanceof ClearEnemy) //a ClearEnemy action cannot kill an enemy.
			return;
		for(int i = enemies().size() - 1; i >= 0; i--) {
			Enemy e = enemies().get(i);
			if(e.isDead()) {
				stack().push(new ClearEnemy(top, e));
			}
		}
	}
	
	/** Assumes {@link #state} is {@link CombatState#ENEMY_TURN} or {@link CombatState#PREP}.
	 * Sets the {@link #state} to {@link CombatState#PLAYER_TURN}. Increments {@link #turn} and sets
	 * {@link #cardsPlayedThisTurn()} to {@code 0}. Does not call {@link #resume()}. */
	private void stackStartPlayerTurn() {
		turn++;
		cardsPlayedThisTurn = 0;
		state = CombatState.PLAYER_TURN;
		stack().push(new GenerateSOT());
		stack().push(new SetEnergy(DEFAULT_ENERGY));
		for(int i = 1; i <= DEFAULT_DRAW; i++)
			stack().push(new SimpleDrawRequest());
		stack().push(new UpdateEnemyIntents());
		stack().push(new SOTLoseBlock());
	}
	
	/** Does <em><strong>NOT</strong></em> call {@link #resume()}. */
	private void pushEndTurnActions() {
		List<Card> cards = hand().cards();
		List<Enemy> enemies = enemies();
		for(int i = enemies.size() - 1; i >= 0; i--)
			stack().push(new EOTEnemyLoseBlock(enemies.get(i)));
		for(int i = cards.size() - 1; i >= 0; i--) {
			Card c = cards.get(i);
			if(c instanceof Guilt) {
				stack().push(new ReturnToDrawPile(c, null));
			}
			else {
				stack().push(new EOTDiscard(c));
			}
		}
		stack().pushReversed(EOTEffects.apply());
	}
	
	/** An "explicit" end turn is when the player requests the turn be ended (e.g. by clicking an "End Turn" button). */
	public boolean canEndTurnExplicity() {
		return !isRunning() && stack().isEmpty();
	}
	
	/** Calls {@link #resume()}. */
	public void endPlayerTurn() {
		if(!canEndTurnExplicity())
			throw new IllegalStateException("Cannot end turn explicitly");
		state = CombatState.PLAYER_TO_ENEMY;
		//TODO if needed, we can add an action to the stack here whose only purpose is to notify the visual components
		//that the turn has ended.
		pushEndTurnActions();
		resume();
	}
	
	/** Should only be called by {@link ForcedEndTurn#execute()}. Does <em><strong>NOT</strong></em> call
	 * {@link #resume()}.*/
	public void endPlayerTurnForcefully() {
		state = CombatState.PLAYER_TO_ENEMY;
		pushEndTurnActions();
	}
	
	/** Assumes the {@link #state} is {@link CombatState#PLAYER_TO_ENEMY}. Sets {@link #state} to
	 * {@link CombatState#ENEMY_TURN}. Does not call {@link #resume()}. Should only be called by {@link StartEnemyTurn}.
	 */
	public void startEnemyTurn() {
		state = CombatState.ENEMY_TURN;
		List<Enemy> enemies = enemies();
		for(int i = enemies.size() - 1; i >= 0; i--)
			stack().pushReversed(enemies.get(i).getActions());
	}

	/** Return {@code false} if the cards cannot be passed to the {@link CardAccepting} action for any reason.
	 * Calls {@link #resume()}. */
	public boolean requestSupplyCardsToInquiry(List<Card> cards) {
		if(canSupplyCardsToInquiry(cards)) {
			((CardAccepting) stack().peek()).setCards(cards);
			resume();
			return true;
		}
		return false;
	}

	public boolean canSupplyCardsToInquiry(List<Card> cards) {
		return cardInquiry() != null && paused() && !stack().isEmpty() && stack().peek() instanceof CardAccepting &&
				cardInquiry().selection().validate(cards);
	}
	
	public void pause() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public boolean paused() {
		return !isRunning();
	}
	
	public boolean started() {
		return turn > 0;
	}
	
	/** This method simply adds {@code card} to {@link #cardsInPlay()}; it does not play the given {@link Card} or
	 * cause it to be played. Increments {@link #cardsPlayedThisTurn()}. Throws an exception if the given card is
	 * already in play. */
	public void addCardToPlay(Card card) {
		if(!cardsInPlay.add(card))
			throw new IllegalStateException(String.format("Already in play: %s", card));
		cardsPlayedThisTurn++;
	}
	
	/** Throws an exception if the given {@link Card} was not in play. */
	public void discardNaturally(Card card) {
		if(!cardsInPlay.remove(card))
			throw new IllegalStateException(String.format("Not in play: %s", card));
		discardPile.addToTop(card);
	}
	
	public void discardEOT(Card card) {
		hand().removeOrThrow(card);
		discardPile().addToTop(card);
	}
	
	/** Assumes the given {@link Card} is in the {@link Hand}. */
	public void discardFromHandExplicitly(Card card) {
		hand().removeOrThrow(card);
		discardPile().addToTop(card);
	}

	/** Should only be called by {@link RemoveOTFromPlay#execute()}.
	 * Throws an exception if the given {@link Card} was not in play. */
	public void removeOTFromPlay(Card card) {
		if(!cardsInPlay.remove(card))
			throw new IllegalStateException(String.format("Not in play: %s", card));
	}
	
	/** Should only be called by {@link ClearEnemy#execute()}. */
	public void clearEnemy(Enemy enemy) {
		if(enemy.isAlive())
			throw new IllegalArgumentException(String.format("Enemy is alive: %s", enemy));
		if(!enemies.remove(enemy))
			throw new IllegalArgumentException(String.format("Enemy is not in this combat: %s", enemy));
	}
	
	/** Returns {@code 0} if the player's first turn hasn't started yet. */
	public int turn() {
		return turn;
	}
	
	public int cardsPlayedThisTurn() {
		return cardsPlayedThisTurn;
	}
	
	public ActionStack stack() {
		return stack;
	}
	
	/** Unmodifiable. */
	public Set<Card> cardsInPlay() {
		return Collections.unmodifiableSet(cardsInPlay);
	}
	
	/** Returns a {@link Set} of all {@link Card Cards} that are currently in this {@link Combat}. This includes all
	 * cards in the discard pile, draw pile, and hand, as well as cards that are currently being played. This does
	 * not include OT cards that have been used, nor does it include cards from the {@link Deck}. */
	public Set<Card> cardsInCombat() {
		Set<Card> cards = new HashSet<>();
		for(Card c : cardsInPlay)
			cards.add(c);
		for(Card c : hand())
			cards.add(c);
		for(Card c : drawPile())
			cards.add(c);
		for(Card c : discardPile())
			cards.add(c);
		return cards;
	}
	
	public DrawPile drawPile() {
		return drawPile;
	}
	
	public DiscardPile discardPile() {
		return discardPile;
	}
	
	public Hand hand() {
		return hand;
	}
	
	public Energy energy() {
		return energy;
	}
	
	/** Returns {@code null} if not {@link #setInquiry(CardInquiry) set}. */
	public CardInquiry cardInquiry() {
		return cardInquiry;
	}
	
	/** Pauses the {@link Combat}. @throws NullPointerException if {@code inquiry} is {@code null}. */
	public void setInquiry(CardInquiry inquiry) {
		Objects.requireNonNull(inquiry);
		if(cardInquiry() != null)
			throw new IllegalStateException(String.format("Combat already has an active inquiry: %s", cardInquiry()));
		if(stack().isEmpty())
			throw new IllegalStateException("Stack is empty");
		if(!(stack().peek() instanceof CardAccepting))
			throw new IllegalStateException(String.format("The top Action on the stack is not CardAccepting; it is: %s", 
					stack().peek()));
		this.cardInquiry = inquiry;
		pause();
	}
	
	/** Sets {@link #cardInquiry()} to {@code null}. */
	public void clearInquiry() {
		cardInquiry = null;
	}
	
	/** Unmodifiable. */
	public List<Enemy> enemies() {
		return Collections.unmodifiableList(enemies);
	}
	
	public CombatState state() {
		return state;
	}
	
	@SuppressWarnings("unused") //TODO remove this method eventually.
	private void printStack() {
		ListIterator<Action> itr = stack().iteratorAtTop();
		while(itr.hasPrevious())
			System.out.println(itr.previous());
		System.out.println();
	}
	
	public void debugPrint() {
		System.out.printf("===Combat====================================%n");
		hand().debugPrint();
		System.out.printf("=============================================%n");
	}
	
}
