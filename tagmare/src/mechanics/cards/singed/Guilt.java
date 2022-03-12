package mechanics.cards.singed;

import mechanics.cards.*;

public class Guilt extends AbstractCard implements Singed {

	public Guilt() {
		super(CardTag.GUILT);
	}

	@Override
	public Card copy() {
		return new Guilt();
	}
	
}
