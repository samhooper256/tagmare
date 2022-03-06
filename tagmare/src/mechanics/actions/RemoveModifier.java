package mechanics.actions;

import mechanics.*;
import mechanics.modifiers.*;

/** Removes the {@link Modifier} with the given {@link ModifierTag} from the {@link #target()}. If the modifier is
 * an {@link Modifier#isInteger() integer} modifier, all of it is removed. */
public class RemoveModifier extends AbstractTargettedAction {

	public static RemoveModifier fromPlayer(ModifierTag tag, ActionSource source) {
		return new RemoveModifier(tag, source, Hub.player());
	}
	
	private final ModifierTag tag;
	
	public RemoveModifier(ModifierTag tag, ActionSource source, Entity target) {
		super(source, target);
		this.tag = tag;
	}

	@Override
	public void execute() {
		target().modifiers().removeOrThrow(tag());
	}

	public ModifierTag tag() {
		return tag;
	}
	
}
