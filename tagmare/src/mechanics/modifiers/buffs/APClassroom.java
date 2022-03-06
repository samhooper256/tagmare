package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class APClassroom extends AbstractModifier implements VisibleBuff {

	public APClassroom() {
		
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.AP_CLASSROOM;
	}
	
}
