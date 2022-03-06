package mechanics.cards.attacks;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.KnockedOut;

public class AllNighter extends AbstractCard implements Attack {

	public static final int DAMAGE = 28;
	
	public AllNighter() {
		super(CardTag.ALL_NIGHTER);
	}

	@Override
	public Card copy() {
		return new AllNighter();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(
			new DealDamageToAll(this, DAMAGE),
			new ForcedEndTurn(this),
			ApplyModifier.toPlayer(new KnockedOut(), this)
		);
	}
	
}
