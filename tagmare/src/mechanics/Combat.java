package mechanics;

import java.util.*;

import base.VisualManager;
import mechanics.actions.*;
import mechanics.cards.*;

//TODO support user input other than selecting/targetting cards. (e.g. YOGA).
public final class Combat {

	private final ActionStack stack;
	private final Set<Card> cardsInPlay;
	private final DrawPile drawPile;
	private final DiscardPile discardPile;
	private final Hand hand;
	
	private boolean running;
	private int turn;
	
	public Combat() {
		stack = new ActionStack();
		cardsInPlay = new HashSet<>();
		drawPile = new DrawPile();
		discardPile = new DiscardPile();
		hand = new Hand();
	}
	
	public void requestPlayCardFromHand(Card card) {
		requestPlayCardFromHand(card, null);
	}
	
	public void requestPlayCardFromHand(Card card, Enemy target) {
		//TODO throw exception if the given card is not in the player's hadn OR the given enemy is not in this combat.
		stack().push(new PutCardInPlay(card, Hub.player(), target));
	}
	
	public void resume() {
		running = true;
		while(!stack().isEmpty()) {
			Action top = stack().pop();
			VisualManager.executeAction(top);
			//TODO generate and add-to-stack the effects of playing the action, even if paused().
			if(paused())
				return;
		}
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
	
	/** This method simply adds {@code card} to {@link #cardsInPlay()}; it does not play the given {@link Card} or
	 * cause it to be played. Throws an exception if the given card is already in play. */
	public void addCardToPlay(Card card) {
		if(!cardsInPlay.add(card))
			throw new IllegalStateException(String.format("Already in play: %s", card));
	}
	
	/** Throws an exception if the given {@link Card} was not in play. */
	public void removeCardFromPlay(Card card) {
		if(!cardsInPlay.remove(card))
			throw new IllegalStateException(String.format("Not in play: %s", card));
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
	
}
