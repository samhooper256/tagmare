package mechanics.effects;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

public final class EnemyAttackEffects {

	private EnemyAttackEffects() {
		
	}
	
	public static ActionList apply(Enemy enemy, TakeDamage action) {
		action.setDamage(getModifiedDamage(enemy, action.damage()));
		return Action.list(action);
	}
	
	public static int getModifiedDamage(Enemy enemy, int damage) {
		return damage;
	}
	
}
