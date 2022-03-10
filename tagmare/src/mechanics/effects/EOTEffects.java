package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.modifiers.*;
import mechanics.modifiers.debuffs.Nonsense;

import static mechanics.modifiers.ModifierTag.*;

public final class EOTEffects {

	private EOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		if(pmods.contains(KNOCKED_OUT))
			list.add(RemoveModifier.fromPlayer(KNOCKED_OUT, null));
		if(pmods.contains(TOMATOED))
			list.add(RemoveModifier.fromPlayer(TOMATOED, null));
		if(pmods.contains(MEMORIZING))
				list.add(RemoveModifier.fromPlayer(MEMORIZING, null));
		if(pmods.contains(NONSENSE)) {
			Nonsense n = pmods.getModifierOrThrow(NONSENSE);
			list.add(new TakeDamage(n.integer(), n));
			list.add(RemoveModifier.fromPlayer(NONSENSE, null));
		}
		return list.build();
	}
	
}
