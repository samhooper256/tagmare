package base;

import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.cards.Card;

public class DeckDisplay extends TextFlow {

	public DeckDisplay() {
		getChildren().addAll(new Text("Hey it's me\n"),
				new Text("I'm on a new line\n"));
	}
	
	public void update() {
		getChildren().clear();
		for(Card c : Hub.deck())
			getChildren().add(new Text(String.format("%s\n", c.tag().displayName())));
	}
	
}
