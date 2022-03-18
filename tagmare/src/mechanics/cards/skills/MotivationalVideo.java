package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.mixed.Motivation;

public class MotivationalVideo extends AbstractCard implements Skill {

	public static final int BASE_EFFECTIVENESS = 4;
	
	private int effectiveness;
	
	/** Has {@link #BASE_EFFECTIVENESS}. */
	public MotivationalVideo() {
		this(BASE_EFFECTIVENESS);
	}
	
	public MotivationalVideo(int effectiveness) {
		super(CardTag.MOTIVATIONAL_VIDEO);
		this.effectiveness = BASE_EFFECTIVENESS;
	}
	
	public void decreaseEffectiveness() {
		effectiveness = Math.max(0, effectiveness - 1);
	}
	
	@Override
	public Card copy() {
		return new MotivationalVideo(effectiveness);
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this,
			ApplyModifier.toPlayer(new Motivation(effectiveness()), this),
			new DecreaseMotivationalVideoEffectiveness(this)
		);
	}
	
	@Override
	public void updateText() {
		super.updateText();
		text().set("W", effectiveness);
	}
	
	public int effectiveness() {
		return effectiveness;
	}
	
	@Override
	public String toString() {
		return String.format("MotivationalVideo@%x", hashCode());
	}

}
