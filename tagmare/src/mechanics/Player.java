package mechanics;

import mechanics.actions.*;
import mechanics.cards.Deck;
import mechanics.modifiers.ModifierSet;

public class Player implements Entity, ActionSource {

	public static final int STARTING_HEALTH = 100;
	
	private final ModifierSet modifiers;
	
	private final Deck deck;
	private final Health health;
	
	public Player() {
		modifiers = new ModifierSet();
		health = new Health(STARTING_HEALTH);
		deck = Deck.createStartingDeck();
	}
	
	public ModifierSet modifiers() {
		return modifiers;
	}

	@Override
	public ActionSourceType type() {
		return ActionSourceType.PLAYER;
	}

	@Override
	public Health health() {
		return health;
	}
	
	public Deck deck() {
		return deck;
	}
	
}
