package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Reserves extends IntegerModifier implements VisibleBuff {

	public Reserves(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.RESERVES;
	}
	
	
}
