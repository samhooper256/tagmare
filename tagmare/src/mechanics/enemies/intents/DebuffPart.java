package mechanics.enemies.intents;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;
import mechanics.modifiers.Modifier;

public class DebuffPart extends ModifierPart {

	public DebuffPart(Modifier modifier) {
		super(modifier);
		if(!modifier().isDebuff())
			throw new IllegalArgumentException(String.format("Not a debuff: %s", modifier));
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		return Action.list(ApplyModifier.toPlayer(modifier(), enemy));
	}

	@Override
	public IntentPartTag tag() {
		return IntentPartTag.DEBUFF;
	}
	
	
}
