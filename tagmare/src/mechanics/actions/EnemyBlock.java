package mechanics.actions;

import mechanics.enemies.Enemy;

public class EnemyBlock extends EnemyTargettedAction {

	private int block;
	
	/** Uses the same {@link Enemy} for the {@link #source()} and {@link #target()}. */
	public EnemyBlock(int block, Enemy sourceAndTarget) {
		this(block, sourceAndTarget, sourceAndTarget);
	}
	
	/** @param target the {@link Enemy} gaining block. */
	public EnemyBlock(int block, ActionSource source, Enemy target) {
		super(source, target);
		this.block = block;
	}
	
	@Override
	public void execute() {
		target().block().gain(block);
	}

	public int block() {
		return block;
	}
	
	public void setBlock(int amount) {
		this.block = amount;
	}
	
}
