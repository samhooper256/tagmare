package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Foresight extends AbstractCard implements Skill {

	public Foresight() {
		super(CardTag.FORESIGHT);
	}

	@Override
	public Card copy() {
		return new Foresight();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new ViewDrawPileInOrder(this));
	}
	
}
