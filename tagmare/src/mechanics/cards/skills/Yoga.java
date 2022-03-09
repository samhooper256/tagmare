package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.input.SRHInquiry;

public class Yoga extends AbstractCard implements Skill {

	public static final int DRAW = 3, MIN_DISCARD = 1, MAX_DISCARD = 3;
	
	public Yoga() {
		super(CardTag.YOGA);
	}

	@Override
	public Card copy() {
		return new Yoga();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		Action[] actions = new Action[DRAW + 2];
		for(int i = 0; i < DRAW; i++)
			actions[i] = new SimpleDrawRequest();
		actions[DRAW] = new SetInquiry(new SRHInquiry(1, 3, "to discard"), this);
		actions[DRAW + 1] = new YogaDiscard(this);
		return SkillEffects.apply(this, actions);
	}
	
}
