package mechanics.modifiers.debuffs;

import mechanics.modifiers.*;

/** If you have 2 Tired, you will lose 2 energy next turn and both Tireds will wear off. */
public class Tired extends IntegerModifier implements VisibleDebuff {

	public Tired(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.TIRED;
	}

	
}
