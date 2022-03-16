package mechanics.cards.singed;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;

public class Resentment extends AbstractCard implements Singed {

	public static final int DAMAGE = 6;
	
	public Resentment() {
		super(CardTag.RESENTMENT);
	}
	
	@Override
	public Card copy() {
		return new Resentment();
	}

	@Override
	public ActionList generateEDActions() {
		return Action.list(new DealDamageToAll(DAMAGE, this));
	}
	
}
