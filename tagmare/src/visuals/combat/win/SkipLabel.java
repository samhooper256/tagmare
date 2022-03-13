package visuals.combat.win;

import javafx.scene.control.Label;
import visuals.GameScene;

public class SkipLabel extends Label {

	public static final double Y = SkipArrow.Y + 55;
	private static final double X_OFFSET = -10;
	
	private static final String CSS = "skip-label";
	
	public SkipLabel() {
		super("skip");
		getStyleClass().add(CSS);
		setMouseTransparent(true);
		setLayoutY(Y);
		layoutXProperty().bind(widthProperty().multiply(-.5).add(GameScene.CENTER_X + X_OFFSET));
	}
	
}
