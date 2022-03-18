package mechanics.modifiers.debuffs;

import mechanics.modifiers.Modifier;

/** There are two kinds of {@link Debuff Debuffs}: {@link VisibleDebuff VisibleDebuffs} and
 * {@link HiddenDebuff HiddenDebuffs}. */
public interface Debuff extends Modifier {

	@Override
	default boolean isBuff() {
		return false;
	}
	
}
