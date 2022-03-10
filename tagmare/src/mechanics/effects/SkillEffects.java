package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.modifiers.ModifierSet;

import static mechanics.modifiers.ModifierTag.*;

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
		if(action instanceof GainBlock) {
			GainBlock gb = (GainBlock) action;
			gb.setBlock(getModifiedBlock(card, gb.block()));
		}
		return action;
	}

	public static int getModifiedBlock(Skill card, int block) {
		ModifierSet pmods = Hub.player().modifiers();
		int result = block;
		if(pmods.contains(CONCENTRATION))
			result += pmods.getModifierOrThrow(CONCENTRATION).integer();
		return result;
	}
	
}
