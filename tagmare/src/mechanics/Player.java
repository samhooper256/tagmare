package mechanics;

import mechanics.actions.*;
import mechanics.cards.Deck;
import mechanics.modifiers.ModifierSet;

public class Player implements Entity, ActionSource {

	public static final int STARTING_HEALTH = 100;
	
	private final ModifierSet modifiers;
	
	private final Deck deck;
	
	private int health, maxHealth;
	
	public Player() {
		modifiers = new ModifierSet();
		maxHealth = STARTING_HEALTH;
		health = maxHealth;
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
	public int health() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		if(health < 0 || health > maxHealth)
			throw new IllegalArgumentException(
					String.format("Invalid health (%d) for Player with max health %d", health, maxHealth));
		this.health = health;
	}
	
	public int maxHealth() {
		return maxHealth;
	}
	
	/** Updates {@link #health()} if the new {@code maxHealth} is less than {@code health()}. */
	public void setMaxHealth(int maxHealth) {
		if(maxHealth < 0)
			throw new IllegalArgumentException(String.format("Invalid health: %d", maxHealth));
		this.maxHealth = maxHealth;
		health = Math.min(health, maxHealth);
	}
	
	public Deck deck() {
		return deck;
	}
	
}
