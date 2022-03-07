package mechanics.cards.attacks;

import mechanics.actions.BluffDamage;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class Bluff extends AbstractCard implements Attack {

	public static final int DAMAGE = 9;
	
	public Bluff() {
		super(CardTag.BLUFF);
	}

	@Override
	public Card copy() {
		return new Bluff();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new BluffDamage(DAMAGE, this, target));
	}
	
}
