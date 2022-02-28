package mechanics.actions;

import mechanics.Hub;
import mechanics.enemies.Enemy;

public class UpdateEnemyIntents extends AbstractAction {

	public UpdateEnemyIntents() {
		super(null);
	}
	
	@Override
	public void execute() {
		for(Enemy e : Hub.enemies())
			e.updateIntent();
	}
	
}
