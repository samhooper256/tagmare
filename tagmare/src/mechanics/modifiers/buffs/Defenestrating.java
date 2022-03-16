package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Defenestrating extends IntegerModifier implements HiddenBuff {

	public Defenestrating(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.DEFENESTRATING;
	}
	
}
