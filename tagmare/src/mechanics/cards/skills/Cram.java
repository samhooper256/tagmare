package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.Tired;

public class Cram extends AbstractCard implements Skill {

	public static final int BLOCK = 18;

	public Cram() {
		super(CardTag.CRAM);
	}

	@Override
	public Card copy() {
		return new Cram();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new GainBlock(BLOCK, this), ApplyModifier.toPlayer(new Tired(1), this));
	}
	
	
}
