package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;
import mechanics.enemies.Enemy;

public class DivideAndConquer extends AbstractCard implements Attack {

	public static final int DAMAGE = 4, TIMES = 3;
	
	public DivideAndConquer() {
		super(CardTag.DIVIDE_AND_CONQUER);
	}

	@Override
	public Card copy() {
		return new DivideAndConquer();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(new DACTail(TIMES, this));
	}
	
}
