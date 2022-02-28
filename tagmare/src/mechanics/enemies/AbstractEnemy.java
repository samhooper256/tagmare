package mechanics.enemies;

import mechanics.*;
import mechanics.enemies.intents.Intent;

abstract class AbstractEnemy implements Enemy {

	private final Health health;
	
	protected Intent intent;
	
	protected AbstractEnemy(int maxHealth) {
		health = new Health(maxHealth);
		intent = null;
	}
	
	@Override
	public final void updateIntent() {
		intent = generateIntent();
	}
	
	/** Assumes {@link Combat#turn()} has been incremented to the turn this {@link Intent} is for. */
	protected abstract Intent generateIntent();
	
	@Override
	public Health health() {
		return health;
	}
	
	@Override
	public Intent intent() {
		return intent;
	}
	
}
