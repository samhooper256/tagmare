package mechanics.cards.attacks;

import mechanics.actions.DealDamage;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.ModifierTag;

public class Quizlet extends AbstractCard implements Attack {

	public static final int DAMAGE = 14;
	
	public Quizlet() {
		super(CardTag.QUIZLET);
	}

	@Override
	public Card copy() {
		return new Quizlet();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new DealDamage(DAMAGE, this, target));
	}

	@Override
	public boolean isLegal(Enemy target) {
		return super.isLegal(target) && target.modifiers().contains(ModifierTag.AP_CLASSROOM);
	}
	
}
