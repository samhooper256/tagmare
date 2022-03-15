package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Organize extends AbstractCard implements Skill {

	public Organize() {
		super(CardTag.ORGANIZE);
	}

	@Override
	public Card copy() {
		return new Organize();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new OrganizeTrail(this));
	}
	
}
