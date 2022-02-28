package mechanics.cards;

import mechanics.*;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

/** Cards use identity equality. */
public interface Card extends ActionSource, Comparable<Card> {
	
	Card copy();
	
	CardTag tag();
	
	/** The default implementation only checks if the {@link Player} has enough {@link Energy} and that the targetting
	 * matches up. */
	default boolean isLegal(Enemy target) {
		return (isTargetted() ^ (target == null)) && energyCost() <= Hub.energy().amount();
	}
	
	/** if this {@link Card} is not {@link #isTargetted() targetted}, the parameter is ignored. */
	ActionList generateActions(Enemy target);
	
	default int energyCost() {
		return tag().energyCost();
	}
	
	default boolean isTargetted() {
		return tag().isTargetted();
	}
	
	@Override
	default ActionSourceType type() {
		return ActionSourceType.CARD;
	}
	
	@Override
	default int compareTo(Card o) {
		return tag().compareTo(o.tag());
	}
	
}