package mechanics.effects;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

public final class EnemyBlockEffects {

	private EnemyBlockEffects() {
		
	}
	
	public static ActionList apply(Enemy enemy, EnemyBlock action) {
		action.setBlock(getModifiedBlock(enemy, action.block()));
		return Action.list(action);
	}
	
	public static int getModifiedBlock(Enemy enemy, int block) {
		return block;
	}
	
}
