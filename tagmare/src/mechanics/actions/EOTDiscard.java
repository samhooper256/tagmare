package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.effects.EOTDiscardEffects;

/** "End of turn" discard. */
public class EOTDiscard extends AbstractAction implements HasCard {

	private final Card card;
	
	public EOTDiscard(Card card) {
		super(null);
		this.card = card;
	}
	
	@Override
	public void execute() {
		Hub.combat().discardEOT(card);
		Hub.stack().pushReversed(EOTDiscardEffects.apply(card));
	}
	
	@Override
	public Card card() {
		return card;
	}
	
}
