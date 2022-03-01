package mechanics.actions;

import mechanics.Entity;

/** This class is for {@link Action Actions} that want to have a {@link #target()} property. The {@link #target()}
 * <em>may still be {@code null}.</em>
 * {@link #canExecute()} is equivalent to {@code super.canExecute() && (target == null || !target().isDead())}.
 * */
abstract class AbstractTargettedAction extends AbstractAction implements TargettedAction {

	private final Entity target;
	
	protected AbstractTargettedAction(ActionSource source, Entity target) {
		super(source);
		this.target = target;
	}
	
	/** {@inheritDoc} <em>This method should only be overridden to provide a more specific return type.</em> */
	@Override
	public Entity target() {
		return target;
	}
	
	@Override
	public boolean canExecute() {
		return super.canExecute() && (target == null || !target().isDead());
	}
	
}
