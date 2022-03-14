package visuals.combat.enemies.intents;

import javafx.scene.image.Image;

public abstract class LabeledIcon extends IntentIcon {

	private final IntentLabel label;
	
	public LabeledIcon(Image image) {
		super(image);
		label = new IntentLabel("?");
		getChildren().add(label);
	}
	
	public IntentLabel label() {
		return label;
	}
	
}
