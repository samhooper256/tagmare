package mechanics.enemies.intents;

import mechanics.actions.*;
import mechanics.enemies.Enemy;

public class BlockIntent implements Intent {

	private int amount;
	
	public BlockIntent(int amount) {
		this.amount = amount;
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		return Action.list(new EnemyBlock(amount, enemy));
	}

	public int amount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return String.format("Block(%d)", amount);
	}
	
}
