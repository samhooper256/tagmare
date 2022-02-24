package modifiers;

public interface VisibleDebuff extends Debuff {

	@Override
	default boolean isVisible() {
		return true;
	}
	
}
