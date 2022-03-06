package mechanics.cards;

import mechanics.*;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;
import mechanics.modifiers.*;

/** {@link Card Cards} must use identity equality. */
public interface Card extends ActionSource, Comparable<Card> {
	
	Card copy();
	
	CardTag tag();
	
	/** The default implementation only checks that
	 * <ul>
	 * <li>the {@link Player} has enough {@link Energy}</li>
	 * <li>the targetting matches up (this is a targetting card and {@code enemy} is not {@code null} or this
	 * is a non-targetting card and {@code enemy} is {@code null}) </li>
	 * <li>the player does not have the {@link KnockedOut} modifier</li>
	 * </ul>*/
	default boolean isLegal(Enemy target) {
		return (isTargetted() ^ (target == null)) && energyCost() <= Hub.energy().amount() &&
				!Hub.player().modifiers().contains(ModifierTag.KNOCKED_OUT);
	}
	
	/** if this {@link Card} is not {@link #isTargetted() targetted}, the parameter is ignored. */
	ActionList generateActions(Enemy target);
	
	default int energyCost() {
		return tag().energyCost();
	}
	
	default boolean isTargetted() {
		return tag().isTargetted();
	}
	
	default boolean isOneTime() {
		return tag().isOneTime();
	}
	
	default String displayName() {
		return tag().displayName();
	}
	
	default String defaultText() {
		return tag().text().defaultText();
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