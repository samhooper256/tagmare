package visuals.combat.ribbon;

import javafx.scene.text.Font;
import mechanics.*;
import visuals.*;
import visuals.combat.AbstractHealthBar;

public class HealthBar extends AbstractHealthBar {

	public static final double WIDTH = 96 * 4, HEIGHT = CombatRibbon.HEIGHT, RADIUS = 6;
	public static final Font FONT = Fonts.UI_30;
	
	public HealthBar() {
		super(WIDTH, HEIGHT, RADIUS, FONT, Hub.player().health());
		setLayoutX(GameScene.CENTER_X - WIDTH * .5);
	}

}