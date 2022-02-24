package effects;

public interface HiddenBuff extends Buff {

	@Override
	default boolean isVisible() {
		return false;
	}
	
}
