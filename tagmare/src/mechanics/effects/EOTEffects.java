package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.modifiers.*;
import mechanics.modifiers.debuffs.Nonsense;

public final class EOTEffects {

	private EOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		if(pmods.contains(ModifierTag.KNOCKED_OUT))
			list.add(new RemoveModifier(ModifierTag.KNOCKED_OUT, null, Hub.player()));
		if(pmods.contains(ModifierTag.NONSENSE)) {
			Nonsense n = pmods.getModifierOrThrow(ModifierTag.NONSENSE);
			list.add(new TakeDamage(n.integer(), n));
			list.add(RemoveModifier.fromPlayer(ModifierTag.NONSENSE, null));
		}
		return list.build();
	}
	
}
