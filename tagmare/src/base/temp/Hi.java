package base.temp;

import javafx.scene.text.*;
import mechanics.actions.Action;

public class Hi extends Text {

	public Hi() {
		setText("HI");
		setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 24));
	}
	
	public void setAction(Action action) {
		setText(String.format("HI - %s", action.getClass().getSimpleName()));
	}
	
}
