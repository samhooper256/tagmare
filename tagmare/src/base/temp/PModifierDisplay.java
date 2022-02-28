package base.temp;

import java.util.StringJoiner;

import javafx.scene.text.Text;
import mechanics.Hub;
import mechanics.modifiers.Modifier;

public class PModifierDisplay extends Text {

	public PModifierDisplay() {
		setText("Modifiers: UPDATE ME");
	}
	
	public void update() {
		StringJoiner j = new StringJoiner(", ");
		for(Modifier m : Hub.player().modifiers())
			j.add(m.toString());
		setText(String.format("Modifiers: %s", j));
	}
	
}
