package mechanics.modifiers.mixed;

import mechanics.modifiers.*;

/** {@link #isBuff() Is a buff} iff {@code integer() >= 0}. */
public class Motivation extends IntegerModifier implements SignMixedModifier {

	public Motivation(int integer) {
		super(integer);
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.MOTIVATION;
	}

}
