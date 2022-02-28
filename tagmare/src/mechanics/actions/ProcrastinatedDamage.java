package mechanics.actions;

import mechanics.enemies.Enemy;
import mechanics.modifiers.Procrastinated;

/** The damage dealt is taken directly from the {@link #source()}; it is not computed until {@link #execute()} is
 * called. */
public class ProcrastinatedDamage extends AbstractTargettedAction {

	public ProcrastinatedDamage(Procrastinated source, Enemy target) {
		super(source, target);
	}
	
	@Override
	public void execute() {
		target().takeDamage(source().integer());
	}
	
	@Override
	public Procrastinated source() {
		return (Procrastinated) super.source();
	}
	
}
