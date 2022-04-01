package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;
import mechanics.cards.attacks.Pomodoro;
import mechanics.cards.skills.Copy;
import mechanics.modifiers.*;

import static mechanics.modifiers.ModifierTag.*;

public final class PlayCardEffects {

	private PlayCardEffects() {
	
	}
	
	public static ActionList apply(Card card) {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		//check that it's not Pomodoro so we don't remove the Tomatoed debuff right after we apply it:
		if(!(card instanceof Pomodoro) && pmods.contains(TOMATOED))
			list.add(RemoveModifier.fromPlayer(TOMATOED, null));
		if(card instanceof Attack && ((Attack) card).usesMotivation() && pmods.contains(MOTIVATION))
			list.add(RemoveModifier.fromPlayer(MOTIVATION, null));
		if(pmods.contains(CHEATING) && !(card instanceof Copy)) {
			list.add(ChangeModifier.decrementPlayer(null, CHEATING));
			list.add(Hub.combat().getPlayBypassedCardAction(card.copy()));
			
		}
		return list.build();
	}
	
}
