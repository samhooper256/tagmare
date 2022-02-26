package mechanics;

import mechanics.actions.*;
import mechanics.cards.Deck;
import mechanics.modifiers.ModifierSet;

public class Player implements Entity {

	public static final int STARTING_HEALTH = 100;
	
	private final ModifierSet modifiers;
	
	private final Deck deck;
	private final Health health;
	private final Block block;
	
	public Player() {
		modifiers = new ModifierSet();
		health = new Health(STARTING_HEALTH);
		deck = Deck.createStartingDeck();
		block = new Block();
	}
	
	public void takeDamage(int damage) {
		int blocked = Math.min(damage, block().amount());
		block().lose(blocked);
		damage -= blocked;
		if(damage > 0)
			health().lose(damage);
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

	public Block block() {
		return block;
	}
	
}
