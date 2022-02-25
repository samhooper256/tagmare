package base.temp;

import javafx.scene.text.*;
import mechanics.*;

public class EnergyDisplay extends TextFlow {

	private final Text text;
	
	EnergyDisplay() {
		text = new Text("NOT UPDATED");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 24));
		getChildren().add(text);
	}
	
	public void update() {
		text.setText(String.format("%d/%d", Hub.energy().amount(), Energy.MAX));
	}
	
}
