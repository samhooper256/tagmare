package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;

public class ReturnToDrawPile extends AbstractAction implements HasCard {

	private final Card card;
	
	public ReturnToDrawPile(Card card) {
		this(card, null);
	}
	
	public ReturnToDrawPile(Card card, ActionSource source) {
		super(source);
		this.card = card;
	}

	@Override
	public void execute() {
		Hub.hand().removeOrThrow(card);
		Hub.drawPile().addToTop(card);
	}

	@Override
	public boolean canExecute() {
		return super.canExecute() && Hub.hand().contains(card);
	}
	
	@Override
	public Card card() {
		return card;
	}
	
}
