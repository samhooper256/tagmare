package mechanics.actions;

import mechanics.enemies.Enemy;

abstract class AbstractTargettedAction extends AbstractAction implements TargettedAction {

	private final Enemy target;
	
	protected AbstractTargettedAction(ActionSource source, Enemy target) {
		super(source);
		this.target = target;
	}
	
	@Override
	public final Enemy target() {
		return target;
	}
	
}
