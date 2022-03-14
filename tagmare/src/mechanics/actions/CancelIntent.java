package mechanics.actions;

import mechanics.enemies.Enemy;
import mechanics.enemies.intents.DoNothing;

public class CancelIntent extends EnemyTargettedAction {

	public CancelIntent(ActionSource source, Enemy target) {
		super(source, target);
	}
	
	@Override
	public void execute() {
		target().setIntent(new DoNothing());
	}
	
}
