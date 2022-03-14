package visuals.combat.enemies.intents;

import mechanics.enemies.intents.BasicBlock;
import visuals.fxutils.Images;

public class BlockIcon extends LabeledIcon {

	private final BasicBlock basicBlock;
	
	public BlockIcon(BasicBlock basicBlock) {
		super(Images.SHIELD_INTENT);
		this.basicBlock = basicBlock;
		update();
	}
	@Override
	public void update() {
		label().setText(String.valueOf(basicBlock.block()));
	}
	
}
