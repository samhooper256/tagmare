package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Packed extends IntegerModifier implements VisibleBuff {

	public Packed(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.PACKED;
	}

}
