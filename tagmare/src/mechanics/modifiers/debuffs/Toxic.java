package mechanics.modifiers.debuffs;

import mechanics.modifiers.*;

public class Toxic extends IntegerModifier implements VisibleDebuff {

	public Toxic(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.TOXIC;
	}
	
}
