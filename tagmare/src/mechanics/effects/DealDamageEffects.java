package mechanics.effects;

import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.ModifierSet;
import mechanics.modifiers.buffs.Shortcuts;
import mechanics.modifiers.debuffs.Procrastinated;

import static mechanics.modifiers.ModifierTag.*;

import mechanics.*;

public final class DealDamageEffects {
	
	private DealDamageEffects() {
		
	}
	
	/** Returns an {@link ActionList} that contains the downstream Deal Damage (DD) effects caused by the
	 * {@link Action}, if any. */
	public static ActionList getDownstream(HasDamage action) {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		if((action.source() instanceof Attack || action.source() instanceof Procrastinated) &&
			action.damage() > 0 && pmods.contains(SHORTCUTS)) {
			Shortcuts s = pmods.getModifier(SHORTCUTS);
			if(action instanceof DealDamageToAll)
				list.add(new DealDamageToAll(s.integer(), s));
			else if(action instanceof TargettedAction && ((TargettedAction) action).target() instanceof Enemy)
				list.add(new DealDamage(s.integer(), s, (Enemy) ((TargettedAction) action).target()));
			else
				throw new UnsupportedOperationException(String.format("Don't know how to handle: %s", action));
		}
		return list.build();
	}
	
}
