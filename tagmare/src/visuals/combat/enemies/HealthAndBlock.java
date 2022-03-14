package visuals.combat.enemies;

import javafx.scene.layout.*;
import mechanics.enemies.Enemy;
import visuals.fxutils.Nodes;

public class HealthAndBlock extends Pane {

	public static final double HEIGHT = BlockIndicator.SIZE;
	
	private final HealthBar healthBar;
	private final BlockIndicator blockIndicator;
	
	public HealthAndBlock(Enemy enemy) {
		healthBar = new HealthBar(enemy);
		healthBar.setLayoutY(.5 * (HEIGHT - healthBar.height()));
		blockIndicator = new BlockIndicator(enemy);
		blockIndicator.setAnchorX(.5 * (healthBar.width() - BlockIndicator.SIZE));
		Nodes.setPrefAndMaxSize(this, healthBar.width(), HEIGHT);
		getChildren().addAll(healthBar, blockIndicator);
	}

	public void update() {
		healthBar().update();
		blockIndicator().update();
	}
	
	public HealthBar healthBar() {
		return healthBar;
	}
	
	public BlockIndicator blockIndicator() {
		return blockIndicator;
	}
	
}
