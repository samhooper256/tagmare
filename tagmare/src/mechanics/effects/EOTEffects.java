package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.modifiers.*;
import mechanics.modifiers.buffs.GoodHabits;
import mechanics.modifiers.buffs.NoSleepGang;
import mechanics.modifiers.debuffs.*;

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
		if(pmods.contains(GOOD_HABITS)) {
			GoodHabits gh = pmods.getModifierOrThrow(GOOD_HABITS);
			list.add(new GainBlock(gh.integer(), gh));
		}
		if(pmods.contains(TOXIC)) {
			Toxic t = pmods.getModifierOrThrow(TOXIC);
			list.add(new TakeDamage(t.integer(), t));
		}
		if(pmods.contains(NONSENSE)) {
			Nonsense n = pmods.getModifierOrThrow(NONSENSE);
			list.add(new TakeDamage(n.integer(), n));
			list.add(RemoveModifier.fromPlayer(NONSENSE, null));
		}
		if(pmods.contains(NO_SLEEP_GANG)) {
			NoSleepGang nsg = pmods.getModifierOrThrow(NO_SLEEP_GANG);
			list.add(ApplyModifier.toPlayer(new Tired(nsg.integer()), nsg));
		}
		return list.build();
	}
	
}
