package visuals.combat.win;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import visuals.GameScene;
import visuals.fxutils.*;

public class DarkGlass extends Pane {

	private static final Color COLOR = Color.grayRgb(0, .8);
	
	public DarkGlass() {
		Nodes.setPrefAndMaxSize(this, GameScene.WIDTH, GameScene.HEIGHT);
		setBackground(Backgrounds.of(COLOR));
	}
	
}
