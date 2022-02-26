package mechanics.actions;

import mechanics.Hub;

public class StartEnemyTurn extends AbstractAction {

	public StartEnemyTurn() {
		super(null);
	}
	
	@Override
	public void execute() {
		Hub.combat().startEnemyTurn();
	}

}
