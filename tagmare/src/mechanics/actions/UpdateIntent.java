package mechanics.actions;

import mechanics.enemies.Enemy;

public class UpdateIntent extends EnemyTargettedAction {

	public UpdateIntent(Enemy enemy) {
		super(null, enemy);
	}

	@Override
	public void execute() {
		target().updateIntent();
	}
	
}
