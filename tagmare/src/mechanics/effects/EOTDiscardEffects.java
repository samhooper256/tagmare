package mechanics.effects;

import mechanics.actions.Action;
import mechanics.actions.list.*;
import mechanics.cards.Card;

public final class EOTDiscardEffects {

	private EOTDiscardEffects() {
		
	}
	
	public static ActionList apply(Card card) {
		ActionListBuilder list = Action.listBuilder();
		EffectUtils.addNoSleepGang(list);
		return list.build();
	}
	
}
