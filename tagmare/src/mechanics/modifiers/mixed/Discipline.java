package mechanics.modifiers.mixed;

import mechanics.modifiers.*;

/** {@link #isBuff() Is a buff} iff {@code integer() >= 0}. */
public class Discipline extends IntegerModifier implements SignMixedModifier {

	public static final double PERCENT = .1;
	
	/** The {@link #integer()} is {@code 1}.*/
	public Discipline() {
		this(1);
	}
	
	public Discipline(int integer) {
		super(integer);
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.DISCIPLINE;
	}

}
