package mechanics;

import mechanics.actions.ActionSource;
import mechanics.modifiers.ModifierSet;

/** There are two kinds of {@link Entity Entities}: {@link Player} and {@link Enemy}. */
public interface Entity extends ActionSource {

	Health health();
	
	Block block();
	
	ModifierSet modifiers();
	
	default void takeDamage(int damage) {
		Utils.takeDamage(damage, health(), block());
	}
	
}
