package base;

import mechanics.*;
import mechanics.actions.Action;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import visuals.animations.Animation;

public interface VisualManager extends Updatable {

	/** Returns the same object every time. */
	static VisualManager get() {
		return VisualManagerHolder.INSTANCE;
	}
	
	/** This method calls {@link Action#execute()} and assumes the given {@link Action} has been removed from the
	 * {@link ActionStack}. */
	void executeAction(Action action);
	
	default void requestPlayCardFromHand(Card card) {
		requestPlayCardFromHand(card, null);
	}
	
	/** Checks the legality of the given {@link Card}. If legal and the {@link Hub#combat() combat} is not
	 * {@link Combat#running() running}, adds playing the card to the {@link ActionStack}.
	 * Returns {@code false} iff the card could not be played. */
	boolean requestPlayCardFromHand(Card card, Enemy target);
	
	default void checkedResume() {
		if(Hub.combat().running())
			throw new IllegalStateException("Cannot resume; Combat is running");
		Hub.combat().resume();
	}
	
	@Override
	default void update(long diff) {
		Animation.manager().update(diff);
	}
	
}
