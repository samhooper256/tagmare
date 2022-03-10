package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.Memorizing;

public class Memorize extends AbstractCard implements Skill {

	public static final int BLOCK = 10;
	
	public Memorize() {
		super(CardTag.MEMORIZE);
	}

	@Override
	public Card copy() {
		return new Memorize();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new GainBlock(BLOCK, this), ApplyModifier.toPlayer(new Memorizing(), this));
	}
	
}
