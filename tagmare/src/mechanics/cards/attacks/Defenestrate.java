package mechanics.cards.attacks;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.ModifierTag;
import mechanics.modifiers.buffs.Defenestrating;

public class Defenestrate extends AbstractCard implements Attack {

	public static final int DAMAGE = 4;
	
	public Defenestrate() {
		super(CardTag.DEFENESTRATE);
	}
	
	@Override
	public Card copy() {
		return new Defenestrate();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		ActionListBuilder list = Action.listBuilder();
		list.add(ApplyModifier.toPlayer(new Defenestrating(DAMAGE), this));
		Hand hand = Hub.hand();
		for(int i = 0; i < hand.size(); i++)
			list.add(new ExplicitDiscard(hand.get(i), this));
		list.add(RemoveModifier.fromPlayer(ModifierTag.DEFENESTRATING, this));
		ActionList built = list.build();
		return built;
	}

}
