package mechanics.input;

import java.util.List;

import mechanics.Hub;
import mechanics.cards.*;

public abstract class HandSelection implements CardSelection {

	public HandSelection() {
		
	}
	
	@Override
	public final boolean validate(List<Card> cards) {
		for(Card c : cards)
			if(!Hub.hand().contains(c))
				return false;
		return validateFromHand(cards);
	}
	
	/** Assumes all the given cards are in the {@link Hand}. */
	public abstract boolean validateFromHand(List<Card> cards);
	
}
