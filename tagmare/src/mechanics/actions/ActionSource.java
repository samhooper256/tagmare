package mechanics.actions;

public interface ActionSource {

	ActionSourceType type();
	
	default boolean isCard() {
		return type() == ActionSourceType.CARD;
	}
	
}
