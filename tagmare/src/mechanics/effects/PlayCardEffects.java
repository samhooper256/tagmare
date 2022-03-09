package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;
import mechanics.cards.attacks.Pomodoro;
import mechanics.modifiers.*;

public final class PlayCardEffects {

	private PlayCardEffects() {
	
	}
	
	public static ActionList apply(Card card) {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		//check that it's not Pomodoro so we don't remove the Tomatoed debuff right after we apply it:
		if(!(card instanceof Pomodoro) && pmods.contains(ModifierTag.TOMATOED))
			list.add(RemoveModifier.fromPlayer(ModifierTag.TOMATOED, null));
		if(card instanceof Attack && pmods.contains(ModifierTag.MOTIVATION))
			list.add(RemoveModifier.fromPlayer(ModifierTag.MOTIVATION, null));
		return list.build();
	}
	
}
