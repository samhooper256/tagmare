package modifiers;

public interface VisibleBuff extends Buff {

	@Override
	default boolean isVisible() {
		return true;
	}
	
}
