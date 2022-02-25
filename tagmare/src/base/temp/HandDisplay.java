package base.temp;

import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.cards.Card;

public class HandDisplay extends VBox {

	private final Text title;
	
	public HandDisplay() {
		title = new Text("Hand:");
		title.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18));
		getChildren().add(title);
	}
	
	public void update() {
		getChildren().subList(1, getChildren().size()).clear();
		for(Card c : Hub.hand())
			getChildren().add(CardRep.of(c));
	}
	
}
