package mechanics.effects;

import mechanics.actions.*;
import mechanics.cards.*;

public final class SkillEffects {

	private SkillEffects() {
		
	}
	
	public static ActionList apply(Skill card, Action... actions) {
		for(Action a : actions)
			applyToSingle(card, a);
		return Action.list(actions);
	}
	
	/** Returns the given {@link Action} after (possibly) mutating it. */
	public static Action applyToSingle(Skill card, Action action) {
		return action; //TODO
	}
	
}
