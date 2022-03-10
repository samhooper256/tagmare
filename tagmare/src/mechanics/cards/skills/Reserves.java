package mechanics.cards.skills;

import mechanics.actions.ReserveEnergy;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Reserves extends AbstractCard implements Skill {

	public static final int ADDITIONAL = 1;
	
	public Reserves() {
		super(CardTag.RESERVES);
	}
	
	@Override
	public Card copy() {
		return new Reserves();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, new ReserveEnergy(this));
	}

}
