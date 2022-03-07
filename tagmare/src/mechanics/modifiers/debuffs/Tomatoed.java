package mechanics.modifiers.debuffs;

import mechanics.cards.*;
import mechanics.modifiers.*;

/** If the player is tomatoed, the next card they play this turn cannot be an attack and must not require more than 1
 * energy. */
public class Tomatoed extends AbstractModifier implements VisibleDebuff {

	public static boolean satisfies(Card c) {
		return !(c instanceof Attack) && c.energyCost() <= 1;
	}
	
	public Tomatoed() {
		
	}
	
	@Override
	public ModifierTag tag() {
		return ModifierTag.TOMATOED;
	}
	
}
