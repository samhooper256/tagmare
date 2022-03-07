package mechanics.cards.attacks;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.AttackEffects;
import mechanics.enemies.Enemy;

public class BeforeMidnight extends AbstractCard implements Attack {

	public static final int DAMAGE = 9, DRAW = 1, MAX_CARDS_PLAYED = 2;
	
	public BeforeMidnight() {
		super(CardTag.BEFORE_MIDNIGHT);
	}

	@Override
	public Card copy() {
		return new BeforeMidnight();
	}

	@Override
	public boolean isLegal(Enemy target) {
		return super.isLegal(target) && Hub.combat().cardsPlayedThisTurn() <= MAX_CARDS_PLAYED;
	}
	
	@Override
	public ActionList generateActions(Enemy target) {
		return AttackEffects.apply(this, new DealDamage(DAMAGE, this, target), new SimpleDrawRequest(this));
	}
	
}
