package mechanics.enemies.intents;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

/** An intent that involves both a block and a single {@link Strike}. */
public class BlockStrike implements BlockIntent, AttackIntent {

	private int block;
	private Strike strike;
	
	public BlockStrike(int block, int damage) {
		this.block = block;
		strike = new Strike(damage);
	}

	@Override
	public ActionList getActions(Enemy enemy) {
		return Action.list(new EnemyBlock(block(), enemy), new TakeDamage(damage(), enemy));
	}

	@Override
	public int block() {
		return block;
	}

	@Override
	public void setBlock(int block) {
		this.block = block;
	}

	public int damage() {
		return strike().damage();
	}
	
	public void setDamage(int damage) {
		strike().setDamage(damage);
	}
	
	public Strike strike() {
		return strike;
	}
	
	@Override
	public String toString() {
		return String.format("Block(%d) / Strike(%d)", block(), damage());
	}
}
