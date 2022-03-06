package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.Card;
import mechanics.modifiers.ModifierTag;
import mechanics.modifiers.buffs.Defenestrating;
import utils.RNG;

public final class ExplicitDiscardEffects {

	private ExplicitDiscardEffects() {
		
	}
	
	public static ActionList apply(ExplicitDiscard ed) {
		return apply(ed.card());
	}
	
	/** Returns a (possibly empty) {@link ActionList} of the {@link Action Actions} that should be executed immediately
	 * <em>after</em> the {@link Card} is discarded. */
	public static ActionList apply(Card card) {
		ActionListBuilder list = Action.listBuilder();
		if(Hub.player().modifiers().contains(ModifierTag.DEFENESTRATING)) {
			Defenestrating d = Hub.player().modifiers().getModifierOrThrow(ModifierTag.DEFENESTRATING);
			list.add(new DealDamage(d.integer(), d, RNG.pickOrThrow(Hub.enemies())));
		}
		return list.build();
	}
	
}
