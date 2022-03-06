package mechanics.modifiers.debuffs;

import mechanics.modifiers.*;

public class KnockedOut extends AbstractModifier implements VisibleDebuff {

	public KnockedOut() {
		
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.KNOCKED_OUT;
	}
	
}
