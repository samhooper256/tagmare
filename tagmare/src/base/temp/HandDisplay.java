package base.temp;

import mechanics.Hub;
import mechanics.cards.Card;

public class HandDisplay extends CardListDisplay {

	HandDisplay() {
		super("Hand:");
	}

	@Override
	Iterable<Card> cards() {
		return Hub.hand();
	}

	
}
