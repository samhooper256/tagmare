package base;

import javafx.scene.paint.Color;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.cards.Card;

public class DeckDisplay extends TextFlow {

	public DeckDisplay() {
	}
	
	public void update() {
		getChildren().clear();
		for(Card c : Hub.deck()) {
			Text text = new Text(String.format("%s\n", c.tag().displayName()));
			text.setFill(Color.RED);
			getChildren().add(text);
		}
	}
	
}
