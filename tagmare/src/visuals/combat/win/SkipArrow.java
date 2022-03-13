package visuals.combat.win;

import visuals.*;
import visuals.fxutils.*;

public class SkipArrow extends Sprite {

	public static final double
		WIDTH = Images.SKIP_ARROW.getWidth(),
		HEIGHT = Images.SKIP_ARROW.getHeight(),
		Y = 720;
	
	public SkipArrow() {
		super(Images.SKIP_ARROW);
		Nodes.setLayout(this, GameScene.CENTER_X - WIDTH * .5, Y);
		setOnMouseClicked(me -> mouseClicked());
		setOnMouseEntered(me -> hoverEntered());
		setOnMouseExited(me -> hoverExited());
	}
	
	private void mouseClicked() {
		Vis.combatEye().startTransition();
	}
	
	private void hoverEntered() {
		setImage(Images.SKIP_ARROW_HOVERED);
	}
	
	private void hoverExited() {
		setImage(Images.SKIP_ARROW);
	}
	
}
