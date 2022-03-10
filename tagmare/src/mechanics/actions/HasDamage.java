package mechanics.actions;

public interface HasDamage extends Action {

	int damage();
	
	/** See {@link ProcrastinatedDamage} for an {@link Action} where the damage cannot be set. */
	default void setDamage(int damage) {
		throw new UnsupportedOperationException("Cannot change the damage");
	}
	
}
