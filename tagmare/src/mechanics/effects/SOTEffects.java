package mechanics.effects;

import mechanics.enemies.Enemy;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;

import static mechanics.modifiers.ModifierTag.*;

public final class SOTEffects {

	private SOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		for(Enemy e : Hub.enemies())
			if(e.modifiers().contains(PROCRASTINATED))
				list.add(new ProcrastinatedDamage(e.modifiers().getModifierOrThrow(PROCRASTINATED), e));
		for(Enemy e : Hub.enemies())
			if(e.modifiers().contains(PROCRASTINATED))
				list.add(new RemoveModifier(PROCRASTINATED, null, e));
		return list.build();
	}
	
}
