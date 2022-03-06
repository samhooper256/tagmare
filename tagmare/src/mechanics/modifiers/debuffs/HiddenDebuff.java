package mechanics.modifiers.debuffs;

public interface HiddenDebuff extends Debuff {

	@Override
	default boolean isVisible() {
		return false;
	}
	
}
