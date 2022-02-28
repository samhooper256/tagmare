package mechanics.actions;

import mechanics.Entity;
import mechanics.modifiers.Modifier;

/** Can be used to apply an {@link Modifier#integer() integer} {@link Modifier} or a non-integer one. */
public class ApplyModifier extends AbstractTargettedAction {

	private final Modifier modifier;
	
	public ApplyModifier(Modifier modifier, ActionSource source, Entity target) {
		super(source, target);
		this.modifier = modifier;
	}
	
	@Override
	public void execute() {
		target().modifiers().add(modifier);
	}
	
	public Modifier modifier() {
		return modifier;
	}
	
}
