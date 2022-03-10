package mechanics.cards.skills;

import mechanics.actions.GainBlock;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Study extends AbstractCard implements Skill {
	
	public static final int BLOCK = 7;
	
	public Study() {
		super(CardTag.STUDY);
	}

	@Override
	public Card copy() {
		return new Study();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new GainBlock(BLOCK, this));
	}
	
}
