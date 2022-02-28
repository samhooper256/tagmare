package mechanics.enemies.intents;

import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

/** <p>An {@link Intent} represents what an {@link Enemy} intends to do (that is, what its action(s) will be on its turn).
 * Every enemy always has exactly 1 {@code Intent}, although one {@code Intent} may spawn multiple
 * {@link #getActions() actions}.</p> */
public interface Intent {

	public ActionList getActions(Enemy enemy);
	
}
