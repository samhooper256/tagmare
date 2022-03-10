package mechanics.modifiers.debuffs;

import mechanics.modifiers.*;

public class Memorizing extends AbstractModifier implements VisibleDebuff {

	public Memorizing() {
		
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.MEMORIZING;
	}

}
