package visuals.combat.win;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import visuals.*;

public class SkipLabel extends Label {

	public static final double Y = SkipArrow.Y + 55, INTRO_START_Y = Y - 20;
	private static final double X_OFFSET = -10;
	
	public SkipLabel() {
		super("skip");
		setMouseTransparent(true);
		setLayoutY(Y);
		layoutXProperty().bind(widthProperty().multiply(-.5).add(GameScene.CENTER_X + X_OFFSET));
		setFont(Fonts.GEORGIA_36_ITALIC);
		setTextFill(Color.WHITE);
	}
	
}
