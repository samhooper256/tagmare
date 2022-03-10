package mechanics.cards.skills;

import mechanics.actions.ChangeEnergy;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Caffeine extends AbstractCard implements Skill {

	public static final int ENERGY = 1;
	
	public Caffeine() {
		super(CardTag.CAFFEINE);
	}

	@Override
	public Card copy() {
		return new Caffeine();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new ChangeEnergy(1, this));
	}
	
	
	
}
