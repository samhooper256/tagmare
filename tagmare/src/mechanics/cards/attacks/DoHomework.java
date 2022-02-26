package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class DoHomework extends AbstractCard implements Attack {

	public static final int DAMAGE = 5;
	
	public DoHomework() {
		super(CardTag.DO_HOMEWORK);
	}
		
	@Override
	public Card copy() {
		return new DoHomework();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new DealDamage(DAMAGE, this, target));
	}
	
}
