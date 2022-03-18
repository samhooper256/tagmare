package mechanics.cards.skills;

import mechanics.actions.ApplyModifier;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Discipline extends AbstractCard implements Skill {

	public Discipline() {
		super(CardTag.DISCIPLINE);
	}

	@Override
	public Card copy() {
		return new Discipline();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, ApplyModifier.toPlayer(new mechanics.modifiers.mixed.Discipline(), this));
	}
	
}
