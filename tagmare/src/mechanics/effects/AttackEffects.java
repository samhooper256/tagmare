package mechanics.effects;

import mechanics.*;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.Attack;
import mechanics.modifiers.*;

/** Any added {@link Action Actions} are added between the actions listed on the card. (For example, if you play Grind
 * and you have Keyboard Shortcuts, the extra 1 damage will be dealt after each of the 5 hits from Grind). */
public final class AttackEffects {

	private AttackEffects() {
		
	}
	
	public static ActionList apply(Attack card, Action... actions) {
		for(Action a : actions)
			applyToSingle(card, a);
		return Action.list(actions);
	}
	
	/** Returns the given {@link Action} after (possibly) mutating it. */
	public static Action applyToSingle(Attack card, Action action) {
		Player p = Hub.player();
		ModifierSet mods = p.modifiers();
		Motivation motivation = mods.getModifier(ModifierTag.MOTIVATION);
		if(motivation != null && action instanceof DealDamage) {
			DealDamage dd = (DealDamage) action;
			dd.setDamage(dd.damage() + motivation.integer());
		}
		//TODO add Discipline
		return action;
	}
	
}
