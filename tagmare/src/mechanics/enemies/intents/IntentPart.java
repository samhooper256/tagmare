package mechanics.enemies.intents;

import mechanics.actions.Action;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

/** <p>{@link IntentPart IntentParts} are immutable and can be shared between {@link Enemy Enemies}. They are not
 * necessarily associated with a single enemy, so they do not necessarily store a reference to any enemy.</p>
 * <p>{@link #isInteger() Integer} parts must override {@link #integer()} and {@link #getModifiedInteger()}.</p>
 * <p>The {@link Action#source()} of {@link #getActions(Enemy) actions} is the enemy, not the {@code IntentPart}.</p> */
public interface IntentPart {

	/** Returns the {@link ActionList} of {@link Action Actions} produced by the given {@code enemy} using this
	 * {@link IntentPart}. The enemy is assumed to be non-{@code null}; behavior is undefined if it is. */
	ActionList getActions(Enemy enemy);
	
	IntentPartTag tag();
	
	/** {@code true} iff this {@link IntentPart} has an integer associated with it. */
	default boolean isInteger() {
		return tag().isInteger();
	} 
	
	default int integer() {
		if(!isInteger())
			throw new UnsupportedOperationException(String.format("This is not an integer IntentPart: %s", this));
		throw new UnsupportedOperationException("Must override.");
	}
	
	default int getModifiedInteger(Enemy enemy) {
		if(!isInteger())
			throw new UnsupportedOperationException(String.format("This is not an integer IntentPart: %s", this));
		throw new UnsupportedOperationException("Must override.");
	}
	
	default String description() {
		return tag().description(isInteger() ? integer() : 0);
	}
	
}
