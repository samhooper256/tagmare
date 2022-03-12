package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.*;

/** Assumes the {@link Card} has already been removed from wherever it used to be (if anywhere). Simply adds the card
 * on top of the {@link DrawPile}. */
public class PlaceCardOnTopOfDrawPile extends AbstractAction implements HasCard {

	private final Card card;
	
	public PlaceCardOnTopOfDrawPile(Card card, ActionSource source) {
		super(source);
		this.card = card;
	}

	@Override
	public void execute() {
		Hub.drawPile().addToTop(card);
	}
	
	@Override
	public Card card() {
		return card;
	}
	
}
