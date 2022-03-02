package visuals;

import base.VisualManager;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;

public final class VisualManagerImpl implements VisualManager {

	@Override
	public void executeAction(final Action action) {
		if(action instanceof SimpleDrawRequest) {
			Hub.combat().pause();
			action.execute();
			SimpleDrawRequest a = (SimpleDrawRequest) action;
			Card card = a.getCard();
			if(card != null)
				Vis.handLayer().startAddCardToRightAnimation(card);
		}
		else {
			action.execute();
		}
	}

	@Override
	public boolean requestPlayCardFromHand(Card card, Enemy target) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void update(long diff) {
		GameScene.get().update(diff);
		VisualManager.super.update(diff);
	}

}
