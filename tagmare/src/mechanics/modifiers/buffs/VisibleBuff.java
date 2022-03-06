package mechanics.modifiers.buffs;

public interface VisibleBuff extends Buff {

	@Override
	default boolean isVisible() {
		return true;
	}
	
}
