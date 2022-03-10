package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class ReadForFun extends AbstractCard implements Skill {

	public static final int BLOCK = 5, DRAW = 1;
	
	public ReadForFun() {
		super(CardTag.READ_FOR_FUN);
	}

	@Override
	public Card copy() {
		return new ReadForFun();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		Action[] actions = new Action[DRAW + 1];
		actions[0] = new GainBlock(BLOCK, this);
		for(int i = 1; i <= DRAW; i++)
			actions[i] = new SimpleDrawRequest(this);
		return SkillEffects.apply(this, actions);
	}
	
}
