package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;

public class RemoveOTFromPlay extends AbstractAction implements HasCard {

	private final Card card;
	
	public RemoveOTFromPlay(Card card) {
		super(null);
		this.card = card;
	}
	
	@Override
	public void execute() {
		Hub.combat().removeOTFromPlay(card);
	}
	
	@Override
	public Card card() {
		return card;
	}
	
}
