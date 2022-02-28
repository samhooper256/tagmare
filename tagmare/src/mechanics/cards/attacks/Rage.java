package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.Enraged;

public class Rage extends AbstractCard implements Attack {

	public static final int DAMAGE = 2, TIMES = 4;
	
	public Rage() {
		super(CardTag.RAGE);
	}

	@Override
	public Card copy() {
		return new Rage();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		Action[] actions = new Action[TIMES + 1];
		for(int i = 0; i < TIMES; i++)
			actions[i] = new DealDamage(DAMAGE, this, target);
		actions[TIMES] = ApplyModifier.toPlayer(new Enraged(1), this);
		return AttackEffects.apply(this, actions);
	}

}
