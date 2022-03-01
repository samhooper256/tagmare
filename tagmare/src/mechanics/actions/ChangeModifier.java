package mechanics.actions;

import mechanics.Entity;
import mechanics.modifiers.*;

/** Changes the {@link Modifier#integer() integer} by the given {@link #amount()}. */
public class ChangeModifier extends AbstractTargettedAction {

	/** Decreases the modifier by {@code 1}. */
	public static ChangeModifier decrement(ActionSource source, Entity target, ModifierTag tag) {
		return new ChangeModifier(source, target, tag, -1);
	}

	private final ModifierTag tag;
	private final int amount;
	
	public ChangeModifier(ActionSource source, Entity target, ModifierTag tag, int amount) {
		super(source, target);
		this.tag = tag;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		target().modifiers().increment(tag, amount);
	}
	
	public ModifierTag tag() {
		return tag;
	}
	
	public int amount() {
		return amount;
	}

}
