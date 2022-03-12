package visuals.calendar.topribbon;

import javafx.scene.layout.Pane;
import visuals.*;
import visuals.fxutils.Images;

public class TopRibbonLayer extends Pane {

	public static final double HEIGHT = Images.CALENDAR_TOP_RIBBON.getHeight();
	
	public TopRibbonLayer() {
		Sprite ribbon = new Sprite(Images.CALENDAR_TOP_RIBBON);
		getChildren().add(ribbon);
	}
	
}
