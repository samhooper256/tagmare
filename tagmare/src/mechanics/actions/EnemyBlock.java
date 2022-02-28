package mechanics.actions;

import mechanics.enemies.Enemy;

public class EnemyBlock extends AbstractAction {

	private final Enemy target;
	
	private int amount;
	
	/** Uses the same {@link Enemy} for the {@link #source()} and {@link #target()}. */
	public EnemyBlock(int amount, Enemy sourceAndTarget) {
		this(amount, sourceAndTarget, sourceAndTarget);
	}
	
	/** @param target the {@link Enemy} gaining block. */
	public EnemyBlock(int amount, ActionSource source, Enemy target) {
		super(source);
		this.target = target;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		target().block().gain(amount);
	}

	public int amount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/** The {@link Enemy} gaining block. */
	public Enemy target() {
		return target;
	}
	
}
