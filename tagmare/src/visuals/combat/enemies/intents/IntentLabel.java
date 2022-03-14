package visuals.combat.enemies.intents;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import visuals.Fonts;

public class IntentLabel extends Label {

	private static final String CSS = "intent-label";
	
	public IntentLabel() {
		this("");
	}
	
	public IntentLabel(String text) {
		super(text);
		setFont(Fonts.UI_30_BOLD);
		setTextFill(Color.WHITE);
		getStyleClass().add(CSS); //sets the black stroke.
	}
	
}
