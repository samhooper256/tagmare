package mechanics;

import java.util.*;

import base.VisualManager;
import mechanics.actions.*;
import mechanics.cards.*;
import mechanics.enemies.*;

//TODO support user input other than selecting/targetting cards. (e.g. YOGA).
//TODO support some kind of "EndTurnFromCard" (ForcedEndTurn) action that ends your turn as a result of a card.
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
	
	private boolean running, playerTurn, enemyTurn;
	private int turn;
	
	public Combat() {
		stack = new ActionStack();
		cardsInPlay = new HashSet<>();
		drawPile = new DrawPile();
		discardPile = new DiscardPile();
		hand = new Hand();
		energy = new Energy();
		enemies = new ArrayList<>();
		enemies.add(new VocabQuiz());
		playerTurn = false;
		enemyTurn = false;
		turn = 0;
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
		while(!stack().isEmpty()) {
			Action top = stack().pop();
			VisualManager.executeAction(top);
			if(paused())
				return;
		}
		if(playerTurn) { //we're waiting for the player to end their turn or play another card.
			running = false;
		}
		else if(enemyTurn) { //enemies have nothing else to do - start the player's next turn.
			enemyTurn = false;
			startPlayerTurn();
		}
		running = false;
	}
	
	/** Assumes {@link #enemyTurn} is {@code false}. Sets {@link #playerTurn} to {@code true}.
	 * Increments {@link #turn}. Calls {@link #resume()}. */
	private void startPlayerTurn() {
		turn++;
		System.out.printf("[enter] startPlayerTurn(), new turn = %d%n", turn);
		playerTurn = true;
		stack().push(new SetEnergy(DEFAULT_ENERGY));
		for(int i = 1; i <= DEFAULT_DRAW; i++)
			stack().push(new SimpleDrawRequest());
		stack().push(new SOTLoseBlock());
		resume();
	}
	
	public boolean canEndTurn() {
		return !running() && stack().isEmpty();
	}
	
	/** Calls {@link #resume()}. */
	public void endPlayerTurn() {
		if(!canEndTurn())
			throw new IllegalStateException("Cannot end turn");
		playerTurn = false;
		List<Card> cards = hand().cards();
		stack().push(new StartEnemyTurn());
		for(int i = cards.size() - 1; i >= 0; i--)
			stack().push(new EOTDiscard(cards.get(i)));
		resume();
	}

	/** Assumes {@link #playerTurn} is {@code false}. Set {@link #enemyTurn} to {@code true}.
	 * Does not call {@link #resume()}. Should only be called by {@link StartEnemyTurn}. */
	public void startEnemyTurn() {
		enemyTurn = true;
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
		hand().remove(card);
		discardPile().addToTop(card);
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
	
}
