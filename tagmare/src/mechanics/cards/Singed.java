package mechanics.cards;

import mechanics.actions.list.ActionList;
import mechanics.effects.*;
import mechanics.enemies.Enemy;

public interface Singed extends Card {

	@Override
	default boolean isLegal(Enemy target) {
		return false;
	}
	
	@Override
	default ActionList generateActions(Enemy target) {
		return ActionList.EMPTY;
	}
	
	/** Should only be called by {@link ExplicitDiscardEffects}. Generates any effects that happen when this card is
	 * explicitly discarded. */
	ActionList generateEDActions();
		
}
