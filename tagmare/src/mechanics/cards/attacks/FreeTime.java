package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class FreeTime extends AbstractCard implements Attack {

	public static final int DAMAGE = 4, BLOCK = 4;
	
	public FreeTime() {
		super(CardTag.FREE_TIME);
	}

	@Override
	public Card copy() {
		return new FreeTime();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new DealDamage(DAMAGE, this, target), new GainBlock(BLOCK, this));
	}
	
}
