package mechanics.cards.skills;

import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;

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
	
	@Override
	public Card copy() {
		return new MotivationalVideo(effectiveness);
	}

	@Override
	public ActionList generateActions(Enemy target) {
		// TODO Auto-generated method stub
		return null;
	}

}
