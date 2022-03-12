package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Cheating extends IntegerModifier implements VisibleBuff {

	public Cheating(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.CHEATING;
	}
	
}
