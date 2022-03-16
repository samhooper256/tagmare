package mechanics.cards.passives;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.*;

public class SpiritualEnlightenment extends AbstractCard implements Passive {

	public static final int STARTING_MOTIVATION = 1, RATE = 1;
	
	public SpiritualEnlightenment() {
		super(CardTag.SPIRITUAL_ENLIGHTENMENT);
	}
	
	@Override
	public Card copy() {
		return new SpiritualEnlightenment();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(
			ApplyModifier.toPlayer(new Enlightened(RATE), this),
			ApplyModifier.toPlayer(new MentalExpansion(STARTING_MOTIVATION), this)
		);
	}
	
}
