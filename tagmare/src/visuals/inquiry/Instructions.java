package visuals.inquiry;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import visuals.*;

public class Instructions extends Label {

	public static double Y = 300;
	public static final String CSS = "inquiry-instructions";
	public static final Font FONT = Fonts.UI_30_BOLD;
	public static final Color COLOR = Color.WHITE;
	
	public Instructions() {
		setFont(FONT);
		setTextFill(COLOR);
		setLayoutY(Y);
		layoutXProperty().bind(widthProperty().multiply(-.5).add(GameScene.CENTER_X));
		getStyleClass().add(CSS);
	}

}
