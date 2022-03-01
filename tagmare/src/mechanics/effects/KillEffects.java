package mechanics.effects;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

public final class KillEffects {

	private KillEffects() {
		
	}
	
	/** Should only be called by {@link ClearEnemy}. */
	public static ActionList apply(Action cause, Enemy killed) {
		return ActionList.EMPTY;
	}
	
}
