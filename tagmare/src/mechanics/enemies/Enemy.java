package mechanics.enemies;

import mechanics.*;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.intents.*;

public interface Enemy extends Entity {

	String name();

	/** Used to explicitly set this {@link Enemy Enemy's} {@link #intent()}. Note that {@link #updateIntent()} should
	 * be used to give the enemy a new {@link Intent} for a new turn. */
	void setIntent(Intent intent);
	
	/** Updates this {@link Enemy Enemy's} {@link #intent()} for the current turn. Assumes that {@link Combat#turn()}
	 * has been incremented to the turn the {@code Intent} is for. */
	void updateIntent();
	
	/** Every {@link Enemy Enemy's} {@link Intent} is {@link DoNothing} by default. */
	Intent intent();
	
	@Override
	default ActionSourceType type() {
		return ActionSourceType.ENEMY;
	}
	
	/** <em>Should not be overridden.</em> */
	default ActionList getActions() {
		return intent().getActions(this);
	}
	
}
