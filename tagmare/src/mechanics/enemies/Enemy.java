package mechanics.enemies;

import mechanics.Entity;
import mechanics.actions.ActionSourceType;

public interface Enemy extends Entity {

	String name();
	
	@Override
	default ActionSourceType type() {
		return ActionSourceType.ENEMY;
	}
	
}
