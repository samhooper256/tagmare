package visuals;

import base.VisualManager;
import base.temp.*;
import mechanics.*;
import mechanics.actions.*;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import visuals.animations.*;

public final class TempVisualManager implements VisualManager {

	public TempVisualManager() {
		
	}
	
	@Override
	public void executeAction(Action action) {
		Hub.combat().pause();
		TempScene.INSTANCE.buttonBar.endTurnButton.setDisable(true);
		Hi hi = TempScene.INSTANCE.hi;
		hi.setOpacity(.2);
		hi.setAction(action);
		Animation.manager().add(new TestFade(hi).withFinisher(() -> animationFinisherWithExecute(action)));
	}
	
	private void animationFinisherWithExecute(Action action) {
		action.execute();
		TempScene.INSTANCE.updateAll();
		Hub.combat().resume();
	}
	
	@Override
	public void requestPlayCardFromHand(Card card) {
		requestPlayCardFromHand(card, null);
	}

	@Override
	public boolean requestPlayCardFromHand(Card card, Enemy target) {
		if(!Hub.combat().running() && card.isLegal(target)) {
			TempScene.INSTANCE.handDisplay.deselect(); //TODO this is temp stuff
			Hub.combat().stackPlayCardFromHand(card, target);
			Hub.combat().resume();
			return true;
		}
		return false;
	}
	
}
