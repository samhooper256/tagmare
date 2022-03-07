package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.Nonsense;

public class WriteNonsense extends AbstractCard implements Attack {

	public static final int DAMAGE = 13, NONSENSE = 4;
	
	public WriteNonsense() {
		super(CardTag.WRITE_NONSENSE);
	}
	
	@Override
	public Card copy() {
		return new WriteNonsense();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(
			this, new DealDamage(DAMAGE, this, target),
			ApplyModifier.toPlayer(new Nonsense(NONSENSE), this)
		);
	}
	
}
