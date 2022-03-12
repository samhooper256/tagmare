package visuals.combat.ribbon;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import visuals.GameScene;
import visuals.fxutils.*;

public class BottomRibbon extends Pane {
	
	public static final double HEIGHT = 60, Y = GameScene.HEIGHT - HEIGHT, HEALTH_BAR_WIDTH = 400;
	
	private final HealthBar healthBar;
	private final Buffs buffs;
	private final Debuffs debuffs;
	private final Shield shield;
	
	public BottomRibbon() {
		setLayoutY(Y);
		Nodes.setPrefAndMaxWidth(this, GameScene.WIDTH);
		setBackground(Backgrounds.of(Color.LIGHTGRAY));
		healthBar = new HealthBar(HEALTH_BAR_WIDTH);
		buffs = new Buffs();
		debuffs = new Debuffs();
		Nodes.setPrefAndMaxSize(buffs, GameScene.CENTER_X - HEALTH_BAR_WIDTH * .5, HEIGHT);
		debuffs.setLayoutX(GameScene.CENTER_X + HEALTH_BAR_WIDTH * .5);
		Nodes.setPrefAndMaxSize(debuffs, GameScene.CENTER_X - HEALTH_BAR_WIDTH * .5, HEIGHT);
		shield = new Shield();
		shield.setLayoutX(GameScene.CENTER_X - Shield.WIDTH * .5);
		shield.setLayoutY(-2);
		getChildren().addAll(healthBar, buffs, debuffs, shield);
	}
	
	/** updates {@link #healthBar()} and {@link #shield()} and calls {@link #updateModifiers()}. */
	public void update() {
		healthBar().update();
		updateModifiers();
		shield().update();
	}
	
	public void updateModifiers() {
		buffs.update();
		debuffs.update();
	}

	public HealthBar healthBar() {
		return healthBar;
	}

	public Shield shield() {
		return shield;
	}
	
}