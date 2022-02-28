package mechanics.enemies.intents;

import mechanics.actions.ActionList;
import mechanics.enemies.Enemy;

public class DoNothing implements Intent {

	public static final DoNothing INSTANCE = new DoNothing();
	
	private DoNothing() {
		
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		return ActionList.EMPTY;
	}
	
	@Override
	public String toString() {
		return "(nothing)";
	}
	
}
