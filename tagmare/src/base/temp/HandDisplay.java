package base.temp;

import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.cards.*;

public class HandDisplay extends VBox {

	private final Text title;
	
	private HandCardRep selected;
	
	public HandDisplay() {
		title = new Text("Hand:");
		title.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18));
		getChildren().add(title);
	}
	
	public void clicked(HandCardRep hcr) {
		if(selected == hcr) {
			hcr.setSelected(false);
			selected = null;
		}
		else {
			if(selected != null)
				selected.setSelected(false);
			selected = hcr;
			hcr.setSelected(true);
		}
	}
	
	public HandCardRep selected() {
		return selected;
	}
	
	public void deselect() {
		if(selected != null)
			selected.setSelected(false);
		selected = null;
	}
	
	public void update() {
		getChildren().subList(1, getChildren().size()).clear();
		Hand hand = Hub.hand();
		for(Card c : hand) {
			getChildren().add(HandCardRep.of(c));
		}
	}
	
}
