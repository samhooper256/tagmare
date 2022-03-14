package visuals.combat.enemies.intents;

import javafx.scene.image.Image;
import visuals.Sprite;

public abstract class IntentIcon extends Sprite {

	public static final double SIZE = 48;
	
	public IntentIcon(Image image) {
		super(image);
	}

	public abstract void update();
	
}
