package mechanics.cards.passives;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.OnLeave;

public class TakeABreak extends AbstractCard implements Passive {

	public static final int CONCENTRATION = 3;
	
	public TakeABreak() {
		super(CardTag.TAKE_A_BREAK);
	}
	
	@Override
	public Card copy() {
		return new TakeABreak();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(new ForcedEndTurn(this), ApplyModifier.toPlayer(new OnLeave(CONCENTRATION), this));
	}

	
}
