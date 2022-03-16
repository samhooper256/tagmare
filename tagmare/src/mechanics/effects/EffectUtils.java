package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.DealDamageToAll;
import mechanics.actions.list.ActionListBuilder;
import mechanics.modifiers.*;
import mechanics.modifiers.buffs.NoSleepGang;

import static mechanics.modifiers.ModifierTag.*;

final class EffectUtils {

	private EffectUtils() {
		
	}
	
	public static void addNoSleepGang(ActionListBuilder list) {
		ModifierSet pmods = Hub.player().modifiers();
		if(pmods.contains(NO_SLEEP_GANG)) {
			NoSleepGang nsg = pmods.getModifierOrThrow(NO_SLEEP_GANG);
			list.add(new DealDamageToAll(nsg.integer(), nsg));
		}
	}
	
}
