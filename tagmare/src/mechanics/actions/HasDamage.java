package mechanics.actions;

public interface HasDamage extends Action {

	int damage();
	
	default void setDamage(int damage) {
		throw new UnsupportedOperationException("Cannot change the damage");
	}
	
}
