package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

/** There are two kinds of {@link Buff Buffs}: {@link VisibleBuff VisibleBuffs} and {@link HiddenBuff HiddenBuffs}.*/
public interface Buff extends Modifier {

	@Override
	default boolean isBuff() {
		return true;
	}
	
}
