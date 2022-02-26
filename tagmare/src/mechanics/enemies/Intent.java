package mechanics.enemies;

import mechanics.actions.ActionList;

/** An {@link Intent} represents what an {@link Enemy} intends to do (that is, what its action(s) will be on its turn).
 * Every enemy always has exactly 1 {@code Intent}, although one {@code Intent} may spawn multiple
 * {@link #getActions() actions}.  */
public interface Intent {

	public ActionList getActions(Enemy enemy);
	
}
