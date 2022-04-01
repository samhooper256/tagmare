package mechanics.cards;

import mechanics.modifiers.ModifierTag;

public interface Attack extends Card {

	/** Returns {@code true} iff {@link ModifierTag#MOTIVATION} applies to this {@link Attack}. */
	default boolean usesMotivation() {
		return true;
	}
	
}
