package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;

public class ReturnFromDiscardPile extends AbstractAction implements HasCard {

	private final Card card;
	
	public ReturnFromDiscardPile(ActionSource source, Card card) {
		super(source);
		this.card = card;
	}

	@Override
	public boolean canExecute() {
		return super.canExecute() && !Hub.hand().isFull() && Hub.discardPile().contains(card);
	}
	
	@Override
	public void execute() {
		Hub.discardPile().removeOrThrow(card);
		Hub.hand().add(card);
	}

	@Override
	public Card card() {
		return card;
	}
	
}
