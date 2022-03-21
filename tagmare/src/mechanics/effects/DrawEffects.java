package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.Card;
import mechanics.modifiers.*;
import mechanics.modifiers.debuffs.Enraged;

public final class DrawEffects {

	private DrawEffects() {
		
	}
	
	public static ActionList apply(Card card) {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		EffectUtils.addNoSleepGang(list);
		if(pmods.contains(ModifierTag.SUGAR_CRASH)) {
			Enraged enraged = pmods.getModifierOrThrow(ModifierTag.SUGAR_CRASH);
			list.add(new ExplicitDiscard(card, enraged));
			list.add(ChangeModifier.decrement(card, Hub.player(), ModifierTag.SUGAR_CRASH));
		}
		return list.build();
	}
	
}
