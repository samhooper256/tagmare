package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class OnLeave extends IntegerModifier implements VisibleBuff {

	public OnLeave(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.ON_LEAVE;
	}
	
}
