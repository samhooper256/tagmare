package mechanics.actions;

import mechanics.Hub;
import mechanics.input.CardInquiry;

public class SetInquiry extends AbstractAction {

	private final CardInquiry inquiry;
	
	public SetInquiry(CardInquiry inquiry, ActionSource source) {
		super(source);
		this.inquiry = inquiry;
	}

	@Override
	public void execute() {
		Hub.combat().setInquiry(inquiry);
	}

	public CardInquiry inquiry() {
		return inquiry;
	}
	
}
