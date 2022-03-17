package visuals.combat.enemies.intents;

import mechanics.enemies.Enemy;
import mechanics.enemies.intents.*;

/** Should call {@link #update(Enemy)} after constructing. */
public class BlockIcon extends LabeledIcon {

	public BlockIcon(BlockPart blockPart) {
		super(blockPart);
	}
	
	@Override
	public void update(Enemy enemy) {
		super.update(enemy);
		label().setText(String.valueOf(intentPart().getModifiedInteger(enemy)));
	}
	
	@Override
	public BlockPart intentPart() {
		return (BlockPart) super.intentPart();
	}
	
}
