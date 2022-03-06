package mechanics.enemies.intents;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

public class BasicBlock implements BlockIntent {

	private int block;
	
	public BasicBlock(int block) {
		this.block = block;
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		return Action.list(new EnemyBlock(block(), enemy));
	}

	@Override
	public int block() {
		return block;
	}

	@Override
	public void setBlock(int block) {
		this.block = block;
	}
	
	@Override
	public String toString() {
		return String.format("Block(%d)", block);
	}
	
}
