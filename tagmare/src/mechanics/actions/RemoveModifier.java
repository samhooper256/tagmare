package mechanics.actions;

import mechanics.Entity;
import mechanics.modifiers.*;

/** Removes the {@link Modifier} with the given {@link ModifierTag} from the {@link #target()}. If the modifier is
 * an {@link Modifier#isInteger() integer} modifier, all of it is removed. */
public class RemoveModifier extends AbstractTargettedAction {

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
