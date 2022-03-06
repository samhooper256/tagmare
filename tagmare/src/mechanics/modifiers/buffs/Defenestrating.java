package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Defenestrating extends IntegerModifier implements VisibleBuff {

	public Defenestrating(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.DEFENESTRATING;
	}
	
}
