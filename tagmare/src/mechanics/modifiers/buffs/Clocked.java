package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Clocked extends IntegerModifier implements VisibleBuff {

	public Clocked(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.CLOCKED;
	}
	
	
}
