package base;

import base.animations.*;
import base.temp.*;
import mechanics.*;
import mechanics.actions.*;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;

public final class VisualManager {

	private VisualManager() {
		
	}
	
	/** This method calls {@link Action#execute()} and assumes the given {@link Action} has been removed from the
	 * {@link ActionStack}. */
	public static void executeAction(Action action) {
		Hub.combat().pause();
		GameScene.INSTANCE.buttonBar.endTurnButton.setDisable(true);
		Hi hi = GameScene.INSTANCE.hi;
		hi.setOpacity(.2);
		hi.setAction(action);
		AbstractAnimation.manager().add(new TestFade(hi).withFinisher(() -> animationFinisherWithExecute(action)));
	}
	
	private static void animationFinisherWithExecute(Action action) {
		action.execute();
		GameScene.INSTANCE.updateAll();
		Hub.combat().resume();
	}
	
	public static void requestPlayCardFromHand(Card card) {
		requestPlayCardFromHand(card, null);
	}

	/** Checks the legality of the given {@link Card}. If legal and the {@link Hub#combat() combat} is not
	 * {@link Combat#running() running}, adds playing the card to the {@link ActionStack}.
	 * Returns {@code false} iff the card could not be played. */
	public static boolean requestPlayCardFromHand(Card card, Enemy target) {
		if(!Hub.combat().running() && card.isLegal()) {
			GameScene.INSTANCE.handDisplay.deselect(); //TODO this is temp stuff
			Hub.combat().stackPlayCardFromHand(card, target);
			Hub.combat().resume();
			return true;
		}
		return false;
	}
	
}
