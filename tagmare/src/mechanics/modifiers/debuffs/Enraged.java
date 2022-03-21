package mechanics.modifiers.debuffs;

import mechanics.Player;
import mechanics.modifiers.*;

/** Only applies to the {@link Player}. */
public class Enraged extends IntegerModifier implements VisibleDebuff {

	public Enraged(int integer) {
		super(integer);
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.SUGAR_CRASH;
	}

}
