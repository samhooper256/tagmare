package effects;

public interface HiddenDebuff extends Debuff {

	@Override
	default boolean isVisible() {
		return false;
	}
	
}
