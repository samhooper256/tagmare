package mechanics.actions;

import mechanics.ActionStack;
import mechanics.actions.list.*;

public interface Action extends ActionSource {

	static ActionList list(Action... actions) {
		return ActionList.of(actions);
	}
	
	static ActionListBuilder listBuilder() {
		return ActionList.builder();
	}
	
	/** Immutable. Returns the same object every time. */
	ActionSource source();
	
	/** <em>Assumes this {@link Action} has been removed from the {@link ActionStack} <strong>before</strong> this
	 * method is called.</em>*/
	void execute();
	
	/** Returns {@code true} iff this {@link Action} can be executed. An action could not be executed if, for example,
	 * its target is dead. The default implementation simply returns {@code true}. */
	default boolean canExecute() {
		return true;
	}
	
	@Override
	default ActionSourceType type() {
		return ActionSourceType.ACTION;
	}
	
}
