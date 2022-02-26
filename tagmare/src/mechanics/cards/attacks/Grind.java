package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class Grind extends AbstractCard implements Attack {

	public static final int DAMAGE = 3, TIMES = 5;
	
	public Grind() {
		super(CardTag.GRIND);
	}
		
	@Override
	public Card copy() {
		return new Grind();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		Action[] damages = new Action[TIMES];
		for(int i = 0; i < damages.length; i++)
			damages[i] = new DealDamage(DAMAGE, this, target);
		return AttackEffects.apply(this, damages);
	}
	
}
