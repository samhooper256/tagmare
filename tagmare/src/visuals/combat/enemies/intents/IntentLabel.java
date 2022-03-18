package visuals.combat.enemies.intents;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import visuals.Fonts;

public class IntentLabel extends Label {

	public static final String CSS = "intent-label";
	public static final Font FONT = Fonts.NUMBERS_30_BOLD;
	
	public IntentLabel() {
		this("");
	}
	
	public IntentLabel(String text) {
		super(text);
		setFont(FONT);
		setTextFill(Color.WHITE);
		setTextAlignment(TextAlignment.CENTER);
		setAlignment(Pos.CENTER);
		getStyleClass().add(CSS); //sets the black stroke.
	}
	
}
