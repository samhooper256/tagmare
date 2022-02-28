package base.temp;

import javafx.scene.text.*;
import mechanics.Hub;

public class BlockDisplay extends Text {

	public BlockDisplay() {
		setText("Block: UPDATE ME");
		setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18));
	}
	
	public void update() {
		setText(String.format("Block: %d", Hub.playerBlock().amount()));
	}
	
}
