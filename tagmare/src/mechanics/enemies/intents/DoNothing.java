package mechanics.enemies.intents;

import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

public class DoNothing implements Intent {

	public DoNothing() {
		
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
