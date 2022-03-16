package mechanics.cards.singed;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;

public class Apathy extends AbstractCard implements Singed {

	public static final int ENERGY = 1;
	
	public Apathy() {
		super(CardTag.APATHY);
	}

	@Override
	public Card copy() {
		return new Apathy();
	}

	@Override
	public ActionList generateEDActions() {
		return Action.list(new ChangeEnergy(ENERGY, this));
	}
	
}
