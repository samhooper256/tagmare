package mechanics.actions;

import java.util.List;

import mechanics.Hub;
import mechanics.actions.list.ActionListBuilder;
import mechanics.cards.Card;
import mechanics.cards.skills.Yoga;

public class YogaDiscard extends AbstractAction implements CardAccepting {

	private List<Card> cards;
	
	public YogaDiscard(Yoga source) {
		super(source);
	}

	@Override
	public void setCards(List<Card> cards) {
		if(this.cards != null)
			throw new IllegalStateException(String.format("Cards have already been set to %s", cards));
		this.cards = cards;
	}

	@Override
	public void execute() {
		ActionListBuilder list = Action.listBuilder();
		for(Card c : cards)
			list.add(new ExplicitDiscard(c, this));
		Hub.stack().pushReversed(list.build());
	}

	@Override
	public List<Card> cards() {
		return cards;
	}
	
	@Override
	public Yoga source() {
		return (Yoga) super.source();
	}
	
}
