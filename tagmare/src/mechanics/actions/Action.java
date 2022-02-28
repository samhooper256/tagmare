package mechanics.actions;

import mechanics.ActionStack;
import mechanics.actions.list.*;

public interface Action {

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
	
}
