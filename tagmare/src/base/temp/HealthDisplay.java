package base.temp;

import javafx.scene.text.*;
import mechanics.*;

public class HealthDisplay extends Text {

	public HealthDisplay() {
		setText("Health: ?/?");
		setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18));
	}
	
	public void update() {
		Health health = Hub.player().health();
		setText(String.format("Health: %d/%d", health.hp(), health.max()));
	}
	
}
