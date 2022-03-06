package visuals.ribbon;

import base.temp.Backgrounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import visuals.GameScene;
import visuals.fxutils.Nodes;

public class BottomRibbon extends Pane {
	
	public static final double HEIGHT = 60, Y = GameScene.HEIGHT - HEIGHT, HEALTH_BAR_WIDTH = 400;
	
	private final HealthBar healthBar;
	private final Buffs buffs;
	private final Debuffs debuffs;
	
	public BottomRibbon() {
		setLayoutY(Y);
		Nodes.setPrefAndMaxWidth(this, GameScene.WIDTH);
		setBackground(Backgrounds.of(Color.LIGHTGRAY));
		healthBar = new HealthBar(HEALTH_BAR_WIDTH);
		buffs = new Buffs();
		debuffs = new Debuffs();
		Nodes.setPrefAndMaxSize(buffs, GameScene.CENTER_X - HEALTH_BAR_WIDTH * .5, HEIGHT);
		Nodes.setPrefAndMaxSize(debuffs, GameScene.CENTER_X - HEALTH_BAR_WIDTH * .5, HEIGHT);
		debuffs.setLayoutX(GameScene.CENTER_X + HEALTH_BAR_WIDTH * .5);
		getChildren().addAll(healthBar, buffs, debuffs);
	}
	
	/** updates {@link #healthBar()} and calls {@link #updateModifiers()}. */
	public void update() {
		healthBar().update();
		updateModifiers();
	}
	
	public void updateModifiers() {
		buffs.update();
		debuffs.update();
	}

	public HealthBar healthBar() {
		return healthBar;
	}
	
}