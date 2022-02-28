package mechanics.modifiers;

/** Only provides a {@link #toString()} which returns the {@link #tag() tag's} {@link ModifierTag#displayName() display
 * name}. */
public abstract class AbstractModifier implements Modifier {

	@Override
	public String toString() {
		return tag().displayName();
	}
	
}
