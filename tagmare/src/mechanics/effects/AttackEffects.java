package mechanics.effects;

import mechanics.*;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.modifiers.*;
import mechanics.modifiers.buffs.Motivation;

/** These are effects that apply to (and therefore modify) the actions produced by a {@link Card}. Attack effects
 * <em>do not</em> add or remove any actions from the list actions a card produces; rather, attack effects simply
 * modify those actions. */
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
