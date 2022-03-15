package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.*;
import mechanics.cards.skills.Organize;

public class OrganizeTrail extends AbstractAction {

	public OrganizeTrail(Organize source) {
		super(source);
	}

	@Override
	public void execute() {
		if(Hub.hand().isFull())
			return; //end the trail - can't add any more cards to hand.
		Card skill = null;
		for(Card c : (Iterable<Card>) Hub.discardPile()::descendingIterator) {
			if(c instanceof Skill) {
				skill = c;
				break;
			}
		}
		if(skill == null)
			return; //end the trail - no more skills to return.
		Hub.stack().pushReversed(new ReturnFromDiscardPile(source(), skill), new OrganizeTrail(source()));
	}

	@Override
	public Organize source() {
		return (Organize) super.source();
	}
	
}
