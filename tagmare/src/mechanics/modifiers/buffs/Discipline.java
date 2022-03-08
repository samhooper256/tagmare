package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Discipline extends IntegerModifier implements VisibleBuff {

	public static final double PERCENT = .1;
	
	public Discipline(int integer) {
		super(integer);
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.DISCIPLINE;
	}

}
