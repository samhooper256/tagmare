package mechanics.effects;

import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.modifiers.ModifierTag;

public final class EOTEffects {

	private EOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		if(Hub.player().modifiers().contains(ModifierTag.KNOCKED_OUT))
			list.add(new RemoveModifier(ModifierTag.KNOCKED_OUT, null, Hub.player()));
		return list.build();
	}
	
}
