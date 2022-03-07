package mechanics.modifiers.debuffs;

import mechanics.modifiers.*;

public class Nonsense extends IntegerModifier implements VisibleDebuff {

	public Nonsense(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.NONSENSE;
	}

	
}
