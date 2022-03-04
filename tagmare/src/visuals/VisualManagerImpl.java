package visuals;

import base.VisualManager;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;

public final class VisualManagerImpl implements VisualManager {

	private boolean waitingOnAnimation;
	
	@Override
	public void executeAction(final Action action) {
		waitingOnAnimation = true;
		Vis.debugLayer().stackDisplay().update();
		if(action instanceof SimpleDrawRequest) {
			Hub.combat().pause();
			action.execute();
			SimpleDrawRequest a = (SimpleDrawRequest) action;
			Card card = a.getCard();
			if(card != null)
				Vis.handLayer().startAddCardToRightAnimation(card);
			else
				Hub.combat().resume();
		}
		else if(action instanceof EOTDiscard) {
			Hub.combat().pause();
			action.execute();
			Card card = ((EOTDiscard) action).card();
			CardRepresentation.of(card).startFlyToDiscard();
		}
		else if(action instanceof RefillDrawPile) {
			action.execute();
			Vis.pileLayer().draw().setCards(Hub.drawPile().trueOrder()); //TODO some kind of animation for this?
		}
		else {
			waitingOnAnimation = false;
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
	
	@Override
	public void checkedResumeFromAnimation() {
		if(Hub.combat().running())
			throw new IllegalStateException("Cannot resume; Combat is running");
		waitingOnAnimation = false;
		Hub.combat().resume();
	}
	
	@Override
	public boolean waitingOnAnimation() {
		return waitingOnAnimation;
	}

}
