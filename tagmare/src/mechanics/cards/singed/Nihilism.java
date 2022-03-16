package mechanics.cards.singed;

import mechanics.actions.*;
import mechanics.actions.list.*;
import mechanics.cards.*;

public class Nihilism extends AbstractCard implements Singed {

	public static final int DRAW = 2;
	
	public Nihilism() {
		super(CardTag.NIHILISM);
	}

	@Override
	public Card copy() {
		return new Nihilism();
	}

	@Override
	public ActionList generateEDActions() {
		ActionListBuilder list = Action.listBuilder();
		for(int i = 0; i < DRAW; i++)
			list.add(new SimpleDrawRequest(this));
		return list.build();
	}
	
}