package visuals.combat.debug;

import java.util.*;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mechanics.Hub;
import mechanics.actions.Action;

public class StackDisplay extends VBox {

	public StackDisplay() {
		
	}
	
	public void update() {
		getChildren().clear();
		List<Text> texts = new ArrayList<>();
		for(Action action : Hub.stack())
			texts.add(new Text(action.toString()));
		Collections.reverse(texts);
		for(Text t : texts)
			getChildren().add(t);
	}
	
}
