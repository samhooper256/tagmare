package mechanics.modifiers.mixed;

import mechanics.modifiers.VisibleModifier;

/** A {@link VisibleModifier} where {@link #isBuff()} returns {@code true} iff {@code integer() >= 0}, {@code false}
 * otherwise. */
public interface SignMixedModifier extends VisibleModifier {

	@Override
	default boolean isBuff() {
		return integer() >= 0;
	}
	
}
