package mechanics.cards.passives;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;

public class NoSleepGang extends AbstractCard implements Passive {

	public static final int BUFF_AMOUNT = 1;
	
	public NoSleepGang() {
		super(CardTag.NO_SLEEP_GANG);
	}
	
	@Override
	public Card copy() {
		return new NoSleepGang();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(ApplyModifier.toPlayer(new mechanics.modifiers.buffs.NoSleepGang(1), this));
	}
	
}
