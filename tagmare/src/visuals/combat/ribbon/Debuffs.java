package visuals.combat.ribbon;

import javafx.geometry.*;
import javafx.scene.layout.HBox;
import mechanics.Hub;
import mechanics.modifiers.Modifier;
import visuals.GameScene;
import visuals.fxutils.Nodes;

public class Debuffs extends HBox {

	private static final double SPACING = Buffs.SPACING, PADDING = Buffs.PADDING, HEIGHT = PlayerModifierIcon.HEIGHT;

	public static final double X = GameScene.CENTER_X + .5 * HealthBar.WIDTH + 144, Y_IN_RIBBON = 2;
	
	public Debuffs() {
		super(SPACING);
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(PADDING));
		setLayoutX(X);
		setLayoutY(Y_IN_RIBBON);
		Nodes.setAllHeights(this, HEIGHT);
	}
	
	public void update() {
		getChildren().clear();
		for(Modifier m : Hub.player().modifiers()) {
			if(m.isDebuff() && m.isVisible()) {
				PlayerModifierIcon icon = PlayerModifierIcon.of(m.tag());
				if(m.isInteger())
					icon.setInteger(m.integer());
				getChildren().add(icon);
			}
		}
	}
	
}
