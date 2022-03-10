package mechanics.effects;

import mechanics.enemies.Enemy;
import mechanics.modifiers.*;
import mechanics.modifiers.buffs.Concentration;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;

import static mechanics.modifiers.ModifierTag.*;

public final class SOTEffects {

	private SOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		if(pmods.contains(ON_LEAVE)) {
			Modifier ol = pmods.getModifierOrThrow(ON_LEAVE);
			list.add(ApplyModifier.toPlayer(new Concentration(ol.integer()), ol));
			list.add(RemoveModifier.fromPlayer(ON_LEAVE, null));
		}
		for(Enemy e : Hub.enemies())
			if(e.modifiers().contains(PROCRASTINATED))
				list.add(new ProcrastinatedDamage(e.modifiers().getModifierOrThrow(PROCRASTINATED), e));
		for(Enemy e : Hub.enemies())
			if(e.modifiers().contains(PROCRASTINATED))
				list.add(new RemoveModifier(PROCRASTINATED, null, e));
		return list.build();
	}
	
}
