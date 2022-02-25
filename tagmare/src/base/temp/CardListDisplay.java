package base.temp;

import javafx.scene.text.*;
import mechanics.cards.Card;

abstract class CardListDisplay extends TextFlow {

	CardListDisplay(String title) {
		Text t = new Text(title + "\n");
		t.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18));
		getChildren().add(t);
	}
	
	public void update() {
		getChildren().subList(1, getChildren().size()).clear();
		for(Card c : cards()) {
			Text text = new Text(String.format("%s\n", c));
			prettify(text);
			getChildren().add(text);
		}
	}
	
	abstract Iterable<Card> cards();
	
	void prettify(Text text) {
		
	}
	
}
