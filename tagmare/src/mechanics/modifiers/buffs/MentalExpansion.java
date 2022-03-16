package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class MentalExpansion extends IntegerModifier implements VisibleBuff {

	public MentalExpansion(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.MENTAL_EXPANSION;
	}
	
	
}
