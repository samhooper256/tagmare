package mechanics.cards.passives;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;

public class GoodHabits extends AbstractCard implements Passive {

	public static final int BLOCK = 3;
	
	public GoodHabits() {
		super(CardTag.GOOD_HABITS);
	}

	@Override
	public Card copy() {
		return new GoodHabits();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(ApplyModifier.toPlayer(new mechanics.modifiers.buffs.GoodHabits(BLOCK), this));
	}
	
}
