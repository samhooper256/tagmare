package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Enlightened extends IntegerModifier implements VisibleBuff {

	public Enlightened(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.ENLIGHTENED;
	}
	
}
