package mechanics.enemies.intents;

import mechanics.actions.EnemyBlock;
import mechanics.actions.list.ActionList;
import mechanics.effects.EnemyBlockEffects;
import mechanics.enemies.Enemy;

public class BlockPart extends IntegerPart {

	public BlockPart(int block) {
		super(block);
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		return EnemyBlockEffects.apply(enemy, new EnemyBlock(block(), enemy));
	}
	
	@Override
	public int getModifiedInteger(Enemy enemy) {
		return EnemyBlockEffects.getModifiedBlock(enemy, block());
	}

	public int block() {
		return integer();
	}
	
	@Override
	public IntentPartTag tag() {
		return IntentPartTag.BLOCK;
	}

}
