package mechanics.effects;

import mechanics.*;
import mechanics.actions.*;
import mechanics.cards.Attack;
import mechanics.modifiers.*;

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
			dd.setAmount(dd.damage() + motivation.integer());
		}
		//TODO add Discipline
		return action;
	}
	
}
