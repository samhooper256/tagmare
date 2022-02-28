package mechanics.actions;

import mechanics.enemies.Enemy;

public class EOTEnemyLoseBlock extends AbstractAction {

	private final Enemy enemy;
	
	public EOTEnemyLoseBlock(Enemy enemy) {
		super(null);
		this.enemy = enemy;
	}
	
	@Override
	public void execute() {
		enemy().block().set(0);
	}
	
	public Enemy enemy() {
		return enemy;
	}
	
}
