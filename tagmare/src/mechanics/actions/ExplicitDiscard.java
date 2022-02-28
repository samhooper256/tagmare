package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.effects.ExplicitDiscardEffects;

public class ExplicitDiscard extends AbstractAction {

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
	
	public Card card() {
		return card;
	}
	
}
