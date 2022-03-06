package visuals.ribbon;

import base.temp.Backgrounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import visuals.GameScene;
import visuals.fxutils.Nodes;

public class BottomRibbon extends Pane {

	public static final double Y = GameScene.HEIGHT - HealthBar.HEIGHT;
	
	private final HealthBar healthBar;
	
	public BottomRibbon() {
		setLayoutY(Y);
		Nodes.setPrefAndMaxWidth(this, GameScene.WIDTH);
		setBackground(Backgrounds.of(Color.LIGHTGRAY));
		healthBar = new HealthBar(400);
		getChildren().add(healthBar);
	}
	
	public void update() {
		healthBar().update();
	}

	public HealthBar healthBar() {
		return healthBar;
	}
	
}