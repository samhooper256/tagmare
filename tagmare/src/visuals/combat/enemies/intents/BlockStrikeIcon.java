package visuals.combat.enemies.intents;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import mechanics.enemies.intents.BlockStrike;
import visuals.fxutils.*;

public class BlockStrikeIcon extends IntentIcon {

	private static final double VBOX_SPACING = -15;
	
	private final BlockStrike blockStrike;
	private final VBox vBox;
	private final IntentLabel damage, block;
	
	public BlockStrikeIcon(BlockStrike blockStrike) {
		super(Images.SHIELD_AND_SWORD_INTENT);
		this.blockStrike = blockStrike;
		damage = new IntentLabel();
		block = new IntentLabel();
		vBox = new VBox(VBOX_SPACING, damage, block);
		vBox.setAlignment(Pos.CENTER);
		vBox.setMinHeight(0);
		getChildren().add(vBox);
		update();
	}

	@Override
	public void update() {
		damage.setText(String.valueOf(blockStrike.damage()));
		block.setText(String.valueOf(blockStrike.block()));
	}
	
}
