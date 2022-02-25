package base;

import mechanics.ActionStack;
import mechanics.actions.Action;

public final class VisualManager {

	private VisualManager() {
		
	}
	
	/** This method calls {@link Action#execute()} and assumes the given {@link Action} has been removed from the
	 * {@link ActionStack}. */
	public static void executeAction(Action action) {
		System.out.printf("EXECUTING: %s%n", action);
		action.execute();
		Main.gs.updateAll();
	}
	
}
