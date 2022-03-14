package mechanics.enemies;

import mechanics.*;
import mechanics.combat.Combat;
import mechanics.enemies.intents.*;

abstract class AbstractEnemy extends AbstractEntity implements Enemy {

	private final EnemyTag tag;
	
	protected Intent intent;

	protected AbstractEnemy(EnemyTag tag, int maxHealth) {
		super(maxHealth);
		this.tag = tag;
		intent = new DoNothing();
	}
	
	/** Assumes {@link Combat#turn()} has been incremented to the turn this {@link Intent} is for. */
	protected abstract Intent generateIntent();
	
	@Override
	public final void updateIntent() {
		intent = generateIntent();
	}
	
	@Override
	public EnemyTag tag() {
		return tag;
	}
	
	@Override
	public Intent intent() {
		return intent;
	}
	
	@Override
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	
}
