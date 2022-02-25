package base.temp;

import javafx.scene.paint.Color;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.cards.Card;

public class DeckDisplay extends CardListDisplay {

	public DeckDisplay() {
		super("Deck");
	}
	
	@Override
	Iterable<Card> cards() {
		return Hub.deck();
	}
	
	@Override
	void prettify(Text text) {
		text.setFill(Color.BROWN);
	}
	
}
