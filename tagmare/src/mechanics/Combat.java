package mechanics;

import java.util.*;

import mechanics.actions.*;
import mechanics.cards.*;
import mechanics.effects.EOTEffects;
import mechanics.enemies.*;
import visuals.VisualManager;

//TODO support user input other than selecting/targetting cards. (e.g. YOGA).
//TODO One-Time cards (e.g. All-Nighter).
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
	private boolean running;
	private int turn;
	private Action mostRecentlyExecuted;
	
	public Combat() {
		stack = new ActionStack();
		cardsInPlay = new HashSet<>();
		drawPile = new DrawPile();
		discardPile = new DiscardPile();
		hand = new Hand();
		energy = new Energy();
		enemies = new ArrayList<>();
		enemies.add(new VocabQuiz());
		enemies.add(new APESProgressCheck());
		state = CombatState.PREP;
		turn = 0;
		mostRecentlyExecuted = null;
	}
	
	/** The card won't actually be played until this {@link Combat} {@link #resume() resumes}.
	 * @throws IllegalStateException if the given {@link Card} is not in the {@link Hand}.
	 * @throws IllegalStateException if the given {@link Enemy} is non-{@code null} and is not an enemy in this combat.
	 * @throws IllegalStateException if {@link #running()}.
	 * @throws IllegalStateException if {@code 
	 * (card.isTargetted() && target == null || !card.isTargetted() && target != null)}*/
	public void stackPlayCardFromHand(Card card, Enemy target) {
		if(running())
			throw new IllegalStateException("Running");
		if(!hand().contains(card))
			throw new IllegalStateException(String.format("Card is not in hand: %s", card));
		if(target != null && !enemies().contains(target))
			throw new IllegalStateException(String.format("Enemy is not in this combat: %s", target));
		if(card.isTargetted() && target == null || !card.isTargetted() && target != null)
			throw new IllegalStateException("Targetting does not match up");
		stack().push(new PutCardInPlay(card, Hub.player(), target));
	}
	
	public void start() {
		if(turn > 0)
			throw new IllegalStateException(String.format("Already started (turn=%d)", turn));
		drawPile.addAllToTop(Hub.deck().shuffledCopyOfCards());
		startPlayerTurn();
	}
	
	public void resume() {
		if(!started())
			throw new IllegalStateException("Not started");
		running = true;
		addClearsIfEnemyKilled(mostRecentlyExecuted);
		while(!stack().isEmpty()) {
			Action top = stack().pop();
			if(top.canExecute()) {
				mostRecentlyExecuted = top;
				VisualManager.executeAction(mostRecentlyExecuted);
				if(paused())
					return;
				else
					addClearsIfEnemyKilled(mostRecentlyExecuted);
			}
		}
		if(state == CombatState.PLAYER_TO_ENEMY) {
			stack().push(new StartEnemyTurn());
			resume();
		}
		if(state == CombatState.ENEMY_TURN) { //enemies have nothing else to do - start the player's next turn.
			startPlayerTurn(); //calls resume
		}
		else {
			running = false;
		}
	}

	/** Adds a {@link ClearEnemy} to the top of the {@link #stack()} for every {@link Enemy} killed by the given
	 * {@link Action}. */
	private void addClearsIfEnemyKilled(Action top) {
		if(top == null)
			return;
		for(int i = enemies().size() - 1; i >= 0; i--) {
			Enemy e = enemies().get(i);
			if(e.isDead())
				stack().push(new ClearEnemy(top, e));
		}
	}
	
	/** Assumes {@link #state} is {@link CombatState#ENEMY_TURN} or {@link CombatState#PREP}.
	 * Sets the {@link #state} to {@link CombatState#PLAYER_TURN}. Increments {@link #turn}. Calls {@link #resume()}. */
	private void startPlayerTurn() {
		turn++;
		state = CombatState.PLAYER_TURN;
		stack().push(new GenerateSOT());
		stack().push(new SetEnergy(DEFAULT_ENERGY));
		for(int i = 1; i <= DEFAULT_DRAW; i++)
			stack().push(new SimpleDrawRequest());
		stack().push(new UpdateEnemyIntents());
		stack().push(new SOTLoseBlock());
		resume();
	}
	
	/** Does <em><strong>NOT</strong></em> call {@link #resume()}. */
	private void pushEndTurnActions() {
		List<Card> cards = hand().cards();
		List<Enemy> enemies = enemies();
		for(int i = enemies.size() - 1; i >= 0; i--)
			stack().push(new EOTEnemyLoseBlock(enemies.get(i)));
		for(int i = cards.size() - 1; i >= 0; i--) {
			stack().push(new EOTDiscard(cards.get(i)));
		}
		stack().pushReversed(EOTEffects.apply());
	}
	
	/** An "explicit" end turn is when the player requests the turn be ended (e.g. by clicking an "End Turn" button). */
	public boolean canEndTurnExplicity() {
		return !running() && stack().isEmpty();
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

	public void pause() {
		running = false;
	}
	
	public boolean running() {
		return running;
	}
	
	public boolean paused() {
		return !running();
	}
	
	public boolean started() {
		return turn > 0;
	}
	
	/** This method simply adds {@code card} to {@link #cardsInPlay()}; it does not play the given {@link Card} or
	 * cause it to be played. Throws an exception if the given card is already in play. */
	public void addCardToPlay(Card card) {
		if(!cardsInPlay.add(card))
			throw new IllegalStateException(String.format("Already in play: %s", card));
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
		discardEOT(card);
	}

	/** Should only be called by {@link RemoveOTFromPlay#execute()}.
	 * Throws an exception if the given {@link Card} was not in play. */
	public void removeOTFromPlay(Card card) {
		if(!cardsInPlay.remove(card))
			throw new IllegalStateException(String.format("Not in play: %s", card));
	}
	
	/** Should only be called by {@link ClearEnemy#execute()}. */
	public void clearEnemy(Enemy enemy) {
		if(!enemies.remove(enemy))
			throw new IllegalArgumentException(String.format("Enemy is not in this combat: %s", enemy));
	}
	
	
	/** Returns {@code 0} if the player's first turn hasn't started yet. */
	public int turn() {
		return turn;
	}
	
	public ActionStack stack() {
		return stack;
	}
	
	/** Unmodifiable. */
	public Set<Card> cardsInPlay() {
		return Collections.unmodifiableSet(cardsInPlay);
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
	
	/** Unmodifiable. */
	public List<Enemy> enemies() {
		return Collections.unmodifiableList(enemies);
	}
	
	@SuppressWarnings("unused") //TODO remove this method eventually.
	private void printStack() {
		ListIterator<Action> itr = stack().iteratorAtTop();
		while(itr.hasPrevious())
			System.out.println(itr.previous());
		System.out.println();
	}
	
}
