package mechanics.cards.skills;

import mechanics.actions.ApplyModifier;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.PlanningAhead;

public class Planner extends AbstractCard implements Skill {

	public static final int BLOCK = 10;
	
	public Planner() {
		super(CardTag.PLANNER);
	}

	@Override
	public Card copy() {
		return new Planner();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, ApplyModifier.toPlayer(new PlanningAhead(BLOCK), this));
	}
	
}
