package mechanics.enemies.intents;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;
import mechanics.modifiers.Modifier;

public class BuffPart extends ModifierPart {

	public BuffPart(Modifier modifier) {
		super(modifier);
		if(!modifier().isBuff())
			throw new IllegalStateException(String.format("Not a buff: %s", modifier));
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		return Action.list(new ApplyModifier(modifier(), enemy, enemy));
	}
	
	@Override
	public IntentPartTag tag() {
		return IntentPartTag.BUFF;
	}

}
