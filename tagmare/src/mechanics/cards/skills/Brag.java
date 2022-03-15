package mechanics.cards.skills;

import mechanics.actions.ApplyModifier;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.Motivation;
import mechanics.modifiers.debuffs.Toxic;

public class Brag extends AbstractCard implements Skill {

	public static final int MOTIVATION = 5, TOXIC = 1;
	
	public Brag() {
		super(CardTag.BRAG);
	}
	
	@Override
	public Card copy() {
		return new Brag();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this,
			ApplyModifier.toPlayer(new Motivation(5), this),
			ApplyModifier.toPlayer(new Toxic(1), this)
		);
	}
	
}
