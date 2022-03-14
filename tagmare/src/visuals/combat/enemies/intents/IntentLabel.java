package visuals.combat.enemies.intents;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import visuals.Fonts;

public class IntentLabel extends Label {

	public static final String CSS = "intent-label";
	
	public IntentLabel() {
		this("");
	}
	
	public IntentLabel(String text) {
		super(text);
		setFont(Fonts.UI_30_BOLD);
		setTextFill(Color.WHITE);
		setTextAlignment(TextAlignment.CENTER);
		setAlignment(Pos.CENTER);
		getStyleClass().add(CSS); //sets the black stroke.
	}
	
}
