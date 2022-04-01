package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.Procrastinated;

public class Procrastinate extends AbstractCard implements Attack {
	
	public static final int DAMAGE = 10;

	public Procrastinate() {
		super(CardTag.PROCRASTINATE);
	}
	
	@Override
	public boolean usesMotivation() {
		return false;
	}
	
	@Override
	public Card copy() {
		return new Procrastinate();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(new ApplyModifier(new Procrastinated(DAMAGE), null, target));
	}

}
