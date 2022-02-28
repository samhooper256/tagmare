package mechanics.modifiers;

import mechanics.Player;

/** Only applies to the {@link Player}. */
public class Enraged extends IntegerModifier implements VisibleDebuff {

	public Enraged(int integer) {
		super(integer);
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.ENRAGED;
	}

}
