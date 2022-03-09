package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.cards.skills.MotivationalVideo;

public class DecreaseMotivationalVideoEffectiveness extends AbstractAction {

	public DecreaseMotivationalVideoEffectiveness(MotivationalVideo source) {
		super(source);
	}
	
	@Override
	public void execute() {
		for(Card c : Hub.combat().cardsInCombat())
			if(c instanceof MotivationalVideo)
				((MotivationalVideo) c).decreaseEffectiveness();
	}
	
}
