package mechanics.cards.attacks;

import mechanics.Hub;
import mechanics.actions.DealDamage;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class PushThrough extends AbstractCard implements Attack {

	public static final int DAMAGE = 11;
	
	public PushThrough() {
		super(CardTag.PUSH_THROUGH);
	}

	@Override
	public Card copy() {
		return new PushThrough();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new DealDamage(DAMAGE, this, target));
	}
	
	@Override
	public boolean isLegal(Enemy target) {
		return super.isLegal(target) && Hub.energy().amount() == 1;
	}
	
}
