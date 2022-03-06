package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.effects.ExplicitDiscardEffects;

public class ExplicitDiscard extends AbstractAction implements HasCard {

	private final Card card;
	
	public ExplicitDiscard(Card card, ActionSource source) {
		super(source);
		this.card = card;
	}
	
	@Override
	public void execute() {
		Hub.combat().discardFromHandExplicitly(card);
		Hub.stack().pushReversed(ExplicitDiscardEffects.apply(card));
	}
	
	@Override
	public Card card() {
		return card;
	}
	
	@Override
	public String toString() {
		return String.format("ExplicitDiscard[card=%s]", card);
	}
	
}
