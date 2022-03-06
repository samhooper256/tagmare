package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Motivation extends IntegerModifier implements VisibleBuff {

	public Motivation(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.MOTIVATION;
	}
	
}
