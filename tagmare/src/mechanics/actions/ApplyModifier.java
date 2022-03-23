package mechanics.actions;

import java.util.Objects;

import mechanics.*;
import mechanics.modifiers.Modifier;

/** Can be used to apply an {@link Modifier#integer() integer} {@link Modifier} or a non-integer one. */
public class ApplyModifier extends AbstractTargettedAction {

	public static ApplyModifier toPlayer(Modifier modifier, ActionSource source) {
		return new ApplyModifier(modifier, source, Hub.player());
	}
	
	private final Modifier modifier;
	
	public ApplyModifier(Modifier modifier, ActionSource source, Entity target) {
		super(source, Objects.requireNonNull(target));
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
