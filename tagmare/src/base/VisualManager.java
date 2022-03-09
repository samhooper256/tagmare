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
	
	/** @throws IllegalArgumentException if can't be played. */
	void playCardFromHand(Card card, Enemy target);
	
	@Override
	default void update(long diff) {
		Animation.manager().update(diff);
	}
	
	/** Optional operation. */
	default void checkedResumeFromAnimation() {
		throw new UnsupportedOperationException("checkedResumeFromAnimation");
	}
	
	/** Optional operation. */
	default boolean waitingOnAnimation() {
		throw new UnsupportedOperationException("waitingOnAnimation");
	}
	
	void startCombat(Combat c);
	
}
