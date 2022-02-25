package mechanics.actions;

import mechanics.ActionStack;

public interface Action {

	static ActionList list(Action... actions) {
		return new ActionList(actions);
	}
	
	/** Immutable. Returns the same object every time. */
	ActionSource source();
	
	/** <em>Assumes this {@link Action} has been removed from the {@link ActionStack} <strong>before</strong> this
	 * method is called.</em>*/
	void execute();
	
}
