package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.cards.skills.Excuse;

public class IncreaseExcuseCost extends AbstractAction {
	
	public IncreaseExcuseCost(Excuse source) {
		super(source);
	}

	@Override
	public void execute() {
		for(Card c : Hub.combat().cardsInCombat())
			if(c instanceof Excuse)
				((Excuse) c).increaseEnergyCost();
	}
	
}
