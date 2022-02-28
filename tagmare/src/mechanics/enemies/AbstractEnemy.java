package mechanics.enemies;

import mechanics.*;
import mechanics.enemies.intents.*;

abstract class AbstractEnemy extends AbstractEntity implements Enemy {

	protected Intent intent;
	
	protected AbstractEnemy(int maxHealth) {
		super(maxHealth);
		intent = DoNothing.INSTANCE;
	}
	
	@Override
	public final void updateIntent() {
		intent = generateIntent();
	}
	
	/** Assumes {@link Combat#turn()} has been incremented to the turn this {@link Intent} is for. */
	protected abstract Intent generateIntent();
	
	@Override
	public Intent intent() {
		return intent;
	}
	
}
