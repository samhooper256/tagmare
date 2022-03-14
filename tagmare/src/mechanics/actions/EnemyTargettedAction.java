package mechanics.actions;

import mechanics.enemies.Enemy;

public abstract class EnemyTargettedAction extends AbstractTargettedAction {

	protected EnemyTargettedAction(ActionSource source, Enemy target) {
		super(source, target);
	}
	
	@Override
	public Enemy target() {
		return (Enemy) super.target();
	}
	
}
