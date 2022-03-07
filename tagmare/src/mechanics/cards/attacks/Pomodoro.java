package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.Tomatoed;

public class Pomodoro extends AbstractCard implements Attack {

	public static final int DAMAGE = 25;
	
	public Pomodoro() {
		super(CardTag.POMODORO);
	}
	
	@Override
	public Card copy() {
		return new Pomodoro();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, 
			new DealDamage(DAMAGE, this, target),
			ApplyModifier.toPlayer(new Tomatoed(), this)
		);
	}

}
