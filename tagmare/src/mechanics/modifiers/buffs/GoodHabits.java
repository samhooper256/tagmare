package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class GoodHabits extends IntegerModifier implements VisibleBuff {

	public GoodHabits(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.GOOD_HABITS;
	}
	
}
