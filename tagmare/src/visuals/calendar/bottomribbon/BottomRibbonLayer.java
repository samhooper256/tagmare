package visuals.calendar.bottomribbon;

import javafx.scene.layout.Pane;
import visuals.*;
import visuals.fxutils.*;

public class BottomRibbonLayer extends Pane {

	public static final double HEIGHT = Images.CALENDAR_BOTTOM_RIBBON.getHeight(), Y = GameScene.HEIGHT - HEIGHT;

	private static final double CONTINUE_Y = Y + 20;
	
	private final Continue continueButton;
	
	public BottomRibbonLayer() {
		setPickOnBounds(false);
		Sprite ribbon = new Sprite(Images.CALENDAR_TOP_RIBBON);
		ribbon.setLayoutY(GameScene.HEIGHT - Images.CALENDAR_BOTTOM_RIBBON.getHeight());
		continueButton = new Continue();
		continueButton.layoutXProperty().bind(continueButton.widthProperty().multiply(-.5).add(GameScene.CENTER_X));
		continueButton.setLayoutY(CONTINUE_Y);
		getChildren().addAll(ribbon, continueButton);
	}
	
	public Continue continueButton() {
		return continueButton;
	}
	
}
