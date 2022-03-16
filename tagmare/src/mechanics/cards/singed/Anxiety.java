package mechanics.cards.singed;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;

public class Anxiety extends AbstractCard implements Singed {

	public static final int BLOCK = 6;
	
	public Anxiety() {
		super(CardTag.ANXIETY);
	}
	
	@Override
	public Card copy() {
		return new Anxiety();
	}

	@Override
	public ActionList generateEDActions() {
		return Action.list(new GainBlock(BLOCK, this));
	}
	
}
