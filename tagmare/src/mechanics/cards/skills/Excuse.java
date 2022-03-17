package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;

public class Excuse extends AbstractCard implements Skill {

	private int energyCost;
	
	public Excuse() {
		this(CardTag.EXCUSE.energyCost());
	}

	private Excuse(int energyCost) {
		super(CardTag.EXCUSE);
		this.energyCost = energyCost;
	}
	
	@Override
	public Card copy() {
		return new Excuse(energyCost);
	}

	@Override
	public ActionList generateActions(Enemy target) {
		if(target.intent().hasAttack())
			return SkillEffects.apply(this, new CancelAttackParts(this, target), new IncreaseExcuseCost(this));
		return ActionList.EMPTY;
	}
	
	@Override
	public boolean isLegal(Enemy target) {
		return super.isLegal(target) && target.intent().hasAttack();
	}
	
	@Override
	public int energyCost() {
		return energyCost;
	}
	
	public void increaseEnergyCost() {
		energyCost++;
	}
	
}
