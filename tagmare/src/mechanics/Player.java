package mechanics;

import mechanics.actions.*;
import mechanics.cards.Deck;

public class Player extends AbstractEntity {

	public static final int STARTING_HEALTH = 100;
	
	private final Deck deck;
	
	public Player() {
		super(STARTING_HEALTH);
		deck = Deck.createStartingDeck();
	}
	
	public void takeDamage(int damage) {
		Utils.takeDamage(damage, health(), block());
	}

	@Override
	public ActionSourceType type() {
		return ActionSourceType.PLAYER;
	}

	public Deck deck() {
		return deck;
	}
	
}
