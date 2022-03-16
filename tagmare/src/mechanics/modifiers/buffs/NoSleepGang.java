package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class NoSleepGang extends IntegerModifier implements VisibleBuff {

	public NoSleepGang(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.NO_SLEEP_GANG;
	}
	
}
