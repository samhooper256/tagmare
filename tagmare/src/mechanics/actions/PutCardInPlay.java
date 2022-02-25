package mechanics.actions;

import mechanics.*;
import mechanics.cards.Card;

public final class PutCardInPlay extends AbstractTargettedAction {
	
	private final Card card;
	
	/** {@code target} may be {@code null} if the {@code card} is not {@link Card#isTargetted() targetted}. */
	public PutCardInPlay(Card card, ActionSource source, Enemy target) {
		super(source, target);
		this.card = card;
	}

	public Card card() {
		return card;
	}

	@Override
	public void execute() {
		Hub.combat().addCardToPlay(card);
		ActionStack stack = Hub.stack();
		stack.push(new RemoveCardFromPlay(card));
		stack.pushReversed(card.generateActions(target()));
	}
	
}
