package mechanics.actions;

import mechanics.*;
import mechanics.effects.KillEffects;
import mechanics.enemies.Enemy;

/** Adds the {@link KillEffects} to the {@link ActionStack}.
 * The {@link #source()} is the action that killed the {@link #enemy()}. */
public final class ClearEnemy extends AbstractAction {

	private final Enemy enemy;
	
	/** @throws IllegalArgumentException if {@code (!enemy.isDead())}. */
	public ClearEnemy(Action source, Enemy enemy) {
		super(source);
		if(!enemy.isDead())
			throw new IllegalArgumentException(String.format("Enemy is not dead: %s", enemy));
		this.enemy = enemy;
	}
	
	@Override
	public void execute() {
		Hub.combat().clearEnemy(enemy);
		Hub.stack().pushReversed(KillEffects.apply(source(), enemy));
	}
	
	@Override
	public Action source() {
		return (Action) super.source();
	}
	
	public Enemy enemy() {
		return enemy;
	}
	
	@Override
	public String toString() {
		return String.format("ClearEnemy[enemy=%s]", enemy);
	}
	
}
