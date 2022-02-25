package base;

import base.temp.GameScene;
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
		GameScene.INSTANCE.updateAll(); //this is temp stuff.
	}
	
}
