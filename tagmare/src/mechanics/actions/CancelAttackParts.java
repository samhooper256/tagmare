package mechanics.actions;

import mechanics.enemies.Enemy;
import mechanics.enemies.intents.*;

public class CancelAttackParts extends EnemyTargettedAction {

	public CancelAttackParts(ActionSource source, Enemy target) {
		super(source, target);
	}
	
	@Override
	public void execute() {
		target().setIntent(target().intent().without(ip -> ip instanceof AttackPart));
	}
	
}
