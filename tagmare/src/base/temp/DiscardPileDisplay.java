package base.temp;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.Hub;
import mechanics.cards.Card;

public class DiscardPileDisplay extends CardListDisplay {

	DiscardPileDisplay() {
		super("Discard Pile:");
	}

	@Override
	Iterable<Card> cards() {
		List<Card> list = new ArrayList<>(Hub.discardPile().trueOrder());
		Collections.reverse(list);
		return list;
	}
	
	@Override
	void prettify(Text text) {
		text.setFill(Color.RED);
	}
	
}
