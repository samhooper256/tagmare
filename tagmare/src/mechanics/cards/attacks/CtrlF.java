package mechanics.cards.attacks;

import mechanics.actions.ChangeHealth;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class CtrlF extends AbstractCard implements Attack {

	public static final int HEALTH_LOSS = 7;
	
	public CtrlF() {
		super(CardTag.CTRL_F);
	}

	@Override
	public Card copy() {
		return new CtrlF();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new ChangeHealth(-HEALTH_LOSS, this, target));
	}
	
	
}
