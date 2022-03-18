package mechanics.modifiers;

public interface VisibleModifier extends Modifier {

	@Override
	default boolean isVisible() {
		return true;
	}
	
}
