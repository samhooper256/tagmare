package mechanics;

import mechanics.actions.ActionSource;
import mechanics.modifiers.ModifierSet;

/** There are two kinds of {@link Entity Entities}: {@link Player} and {@link EnemyRepresentation}. */
public interface Entity extends ActionSource {

	Health health();
	
	Block block();
	
	ModifierSet modifiers();
	
	default void takeDamage(int damage) {
		Utils.takeDamage(damage, health(), block());
	}
	
	default boolean isDead() {
		return health().hp() == 0;
	}
	
	default boolean isAlive() {
		return !isDead();
	}
	
	default boolean isPlayer() {
		return this instanceof Player;
	}
	
}
