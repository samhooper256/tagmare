package mechanics.actions;

import mechanics.enemies.Enemy;

public class EOTEnemyLoseBlock extends EnemyTargettedAction {

	public EOTEnemyLoseBlock(Enemy target) {
		super(null, target);
	}
	
	@Override
	public void execute() {
		target().block().set(0);
	}
	
}
