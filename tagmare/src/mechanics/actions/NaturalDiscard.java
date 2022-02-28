package mechanics.actions;

import mechanics.Hub;
import mechanics.actions.list.ActionList;
import mechanics.cards.Card;
import mechanics.effects.*;

public class NaturalDiscard extends AbstractAction {

	private final Card card;
	
	public NaturalDiscard(Card card) {
		super(null);
		this.card = card;
	}

	@Override
	public void execute() {
		Hub.combat().discardNaturally(card);
		ActionList pc = PlayCardEffects.apply(card);
		Hub.stack().pushReversed(pc);
		ActionList nd = NaturalDiscardEffects.apply(card);
		Hub.stack().pushReversed(nd);
	}
	
	public Card card() {
		return card;
	}
	
}
