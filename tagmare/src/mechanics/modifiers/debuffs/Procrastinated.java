package mechanics.modifiers.debuffs;

import mechanics.enemies.Enemy;
import mechanics.modifiers.*;

/** Only applies to {@link Enemy Enemies}. */
public class Procrastinated extends IntegerModifier implements VisibleDebuff {

	public Procrastinated(int integer) {
		super(integer);
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.PROCRASTINATED;
	}
	
}
