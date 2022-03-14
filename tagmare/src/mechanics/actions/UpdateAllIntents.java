package mechanics.actions;

import mechanics.Hub;
import mechanics.actions.list.ActionListBuilder;
import mechanics.enemies.Enemy;

public class UpdateAllIntents extends AbstractAction {

	public UpdateAllIntents() {
		super(null);
	}
	
	@Override
	public void execute() {
		ActionListBuilder list = Action.listBuilder();
		for(Enemy e : Hub.enemies())
			list.add(new UpdateIntent(e));
		Hub.stack().pushReversed(list.build());
	}
	
}
