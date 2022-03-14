package mechanics.actions;

import mechanics.enemies.Enemy;
import mechanics.modifiers.debuffs.Procrastinated;

/** The damage dealt is taken directly from the {@link #source()}; it is not computed until {@link #execute()} is
 * called. */
public class ProcrastinatedDamage extends EnemyTargettedAction implements HasDamage {

	public ProcrastinatedDamage(Procrastinated source, Enemy target) {
		super(source, target);
	}
	
	@Override
	public void execute() {
		target().takeDamage(damage());
	}
	
	@Override
	public Procrastinated source() {
		return (Procrastinated) super.source();
	}
	
	@Override
	public int damage() {
		return source().integer();
	}
	
}
