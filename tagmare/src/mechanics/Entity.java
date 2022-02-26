package mechanics;

import mechanics.player.Player;

/** There are two kinds of {@link Entity Entities}: {@link Player} and {@link Enemy}. */
public interface Entity {

	Health health();
	
}
