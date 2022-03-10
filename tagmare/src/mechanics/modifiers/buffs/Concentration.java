package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Concentration extends IntegerModifier implements VisibleBuff {

	public Concentration(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.CONCENTRATION;
	}

}
