package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.effects.PlayCardEffects;

public class RemoveCardFromPlay extends AbstractAction {

	private final Card card;
	
	public RemoveCardFromPlay(Card card) {
		super(null);
		this.card = card;
	}

	@Override
	public void execute() {
		Hub.combat().removeCardFromPlay(card);
		ActionList pc = PlayCardEffects.apply(card);
		Hub.stack().pushReversed(pc);
	}
	
}
