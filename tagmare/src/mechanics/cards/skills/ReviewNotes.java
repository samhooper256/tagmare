package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class ReviewNotes extends AbstractCard implements Skill {

	public static final int BLOCK = 5;
	
	public ReviewNotes() {
		super(CardTag.REVIEW_NOTES);
	}

	@Override
	public Card copy() {
		return new ReviewNotes();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new GainBlock(BLOCK, this));
	}
	
}
