package mechanics.cards;

import mechanics.Enemy;
import mechanics.actions.*;

/** Cards use identity equality. */
public interface Card extends ActionSource, Comparable<Card> {
	
	Card copy();
	
	CardTag tag();
	
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