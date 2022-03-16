package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class Shortcuts extends IntegerModifier implements VisibleBuff {

	public Shortcuts(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.SHORTCUTS;
	}
	
}
