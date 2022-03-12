package mechanics.cards;

import mechanics.*;
import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;
import mechanics.modifiers.*;
import mechanics.modifiers.debuffs.*;

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
	 * <li>if the player has the {@link Tomatoed} modifier, it {@link Tomatoed#satisfies(Card) satisfies} its
	 * constraints</li>
	 * </ul>*/
	default boolean isLegal(Enemy target) {
		ModifierSet pmods = Hub.player().modifiers();
		boolean result = (isTargetted() ^ (target == null)) && energyCost() <= Hub.energy().amount() &&
				!pmods.contains(ModifierTag.KNOCKED_OUT);
		if(pmods.contains(ModifierTag.TOMATOED))
			result &= Tomatoed.satisfies(this);
		return result;
	}
	
	/** if this {@link Card} is not {@link #isTargetted() targetted}, the parameter is ignored. */
	ActionList generateActions(Enemy target);
	
	/** Returns {@code -1} iff this card is not {@link #isPlayable() playable}; otherwise, returns a non-negative
	 * number. */
	default int energyCost() {
		return tag().energyCost();
	}
	
	/** Note that playability and legality are different things. Unplayable cards can never be played under any
	 * circumstances; Illegal cards cannot be played under the current circumstances/target (but may be playable under
	 * with different circumstances or with a different target). */
	default boolean isPlayable() {
		return energyCost() >= 0;
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
	
	CardText text();
	
	default void updateText() {
		text().update(this);
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