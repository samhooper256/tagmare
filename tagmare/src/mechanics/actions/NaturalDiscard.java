package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.effects.*;

public class NaturalDiscard extends AbstractAction implements HasCard {

	private final Card card;
	
	public NaturalDiscard(Card card) {
		super(null);
		this.card = card;
	}

	@Override
	public void execute() {
		Hub.combat().discardNaturally(card);
		Hub.stack().pushReversed(PlayCardEffects.apply(card));
		Hub.stack().pushReversed(NaturalDiscardEffects.apply(card));
	}
	
	@Override
	public Card card() {
		return card;
	}
	
}
