package visuals.combat.enemies;

import javafx.scene.text.Font;
import mechanics.Health;
import mechanics.enemies.Enemy;
import visuals.Fonts;
import visuals.combat.AbstractHealthBar;
import visuals.fxutils.Images;

/** {@link HealthBar HealthBars} do not store a reference to the {@link Enemy}, only its {@link Health}. */
public class HealthBar extends AbstractHealthBar {

	public static final double HEIGHT = 20, RADIUS = 8, SIDE_PADDING = 3;
	public static final Font FONT = Fonts.UI_12_BOLD;
	
	public HealthBar(Enemy enemy) {
		super(Images.forEnemy(enemy).getWidth(), HEIGHT, RADIUS, SIDE_PADDING, FONT, enemy.health());
	}
	
}
