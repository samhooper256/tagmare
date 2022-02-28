package mechanics.enemies;

import mechanics.*;
import mechanics.actions.ActionSourceType;
import mechanics.enemies.intents.Intent;

public interface Enemy extends Entity {

	String name();
	
	/** Updates this {@link Enemy Enemy's} {@link #intent()} for the current turn. Assumes that {@link Combat#turn()}
	 * has been incremented to the turn the {@code Intent} is for. */
	void updateIntent();
	
	Intent intent();
	
	@Override
	default ActionSourceType type() {
		return ActionSourceType.ENEMY;
	}
	
}
