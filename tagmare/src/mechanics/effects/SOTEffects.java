package mechanics.effects;

import mechanics.enemies.Enemy;
import mechanics.modifiers.Procrastinated;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;

import static mechanics.modifiers.ModifierTag.*;

public final class SOTEffects {

	private SOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		for(Enemy e : Hub.enemies()) {
			if(e.modifiers().contains(PROCRASTINATED)) {
				Procrastinated p = e.modifiers().getModifierOrThrow(PROCRASTINATED);
				list.add(new ProcrastinatedDamage(p, e));
			}
		}
		//TODO Make procrastinated debuff wear off.
		return list.build();
	}
	
}
