package mechanics;

import mechanics.actions.ActionSource;

/** There are two kinds of {@link Entity Entities}: {@link Player} and {@link Enemy}. */
public interface Entity extends ActionSource {

	Health health();
	
}
