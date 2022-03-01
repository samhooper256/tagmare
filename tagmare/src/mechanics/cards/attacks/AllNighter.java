package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;

public class AllNighter extends AbstractCard implements Attack {

	public AllNighter() {
		super(CardTag.ALL_NIGHTER);
	}

	@Override
	public Card copy() {
		return new AllNighter();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(new ForcedEndTurn());
	}
	
}
