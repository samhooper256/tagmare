package visuals.combat.ribbon;

import javafx.scene.text.Font;
import mechanics.*;
import visuals.*;
import visuals.combat.AbstractHealthBar;

public class HealthBar extends AbstractHealthBar {

	public static final double
		WIDTH = 96 * 4, HEIGHT = CombatRibbon.HEIGHT,
		RADIUS = 6, SIDE_PADDING = 4, TEXT_Y_OFFSET = 0;
	public static final Font FONT = Fonts.NUMBERS_48;
	
	public HealthBar() {
		super(WIDTH, HEIGHT, RADIUS, SIDE_PADDING, TEXT_Y_OFFSET, FONT, Hub.player().health());
		setLayoutX(GameScene.CENTER_X - WIDTH * .5);
	}

}