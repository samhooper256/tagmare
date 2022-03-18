package mechanics.modifiers;

import mechanics.actions.*;
import mechanics.modifiers.buffs.*;
import mechanics.modifiers.debuffs.Debuff;
import mechanics.modifiers.mixed.Motivation;

/** There are two kinds of {@link Modifier Modifiers}: {@link Buff Buffs} and {@link Debuff Debuffs}. All
 * {@link #isInteger() integer} modifiers are mutable. */
public interface Modifier extends ActionSource {

	ModifierTag tag();
	
	boolean isBuff();
	
	/** Equivalent to {@code !isBuff()}. */
	default boolean isDebuff() {
		return !isBuff();
	}
	
	/** Visible {@link Modifier Modifiers} will be shown to the user. */
	boolean isVisible();
	
	/** Hidden {@link Modifier Modifiers} will not be shown to the user. */
	default boolean isHidden() {
		return !isVisible(); 
	}
	
	default String displayName() {
		return tag().displayName();
	}
	
	/** Returns {@code true} iff this {@link Modifier} has an integer value associated with it. For example, see
	 * {@link Motivation}. */
	default boolean isInteger() {
		return tag().isIntegerModifier();
	}
	
	/** <p>Throws an {@link UnsupportedOperationException} if this {@link Modifier} is not an {@link #isInteger() integer}
	 * modifier.</p> 
	 * <p>The default implementation is written such that this method must only be overridden by
	 * integer {@code Modifiers}.</p> */
	default int integer() {
		if(isInteger())
			throw new UnsupportedOperationException("Must override");
		else
			throw new UnsupportedOperationException(
					String.format("This Modifier (%s) is not an integer Modifier", getClass().getSimpleName()));
	}
	
	/** <p>All {@link #isInteger() integer} {@link Modifier Modifiers} are mutable and must support this method. Throws an
	 * {@link UnsupportedOperationException} iff this {@code Modifiers} is not an integer modifier.</p>
	 * <p>This method simply updates the value of {@link #integer()}; it does not cause any downstream effects of that
	 * change to occur.</p>
	 * <p>The default implementation is written such that this method must only be overridden by integer
	 * {@code Modifiers}.</p> */
	default void setInteger(int integer) {
		if(isInteger())
			throw new UnsupportedOperationException("Must override");
		else
			throw new UnsupportedOperationException(
					String.format("This Modifier (%s) is not an integer Modifier", getClass().getSimpleName()));
	}
	
	default void increment() {
		increment(1);
	}
	
	default void increment(int amount) {
		setInteger(integer() + amount);
	}
	
	default void decrement() {
		decrement(1);
	}
	
	default void decrement(int amount) {
		setInteger(integer() - amount);
	}
	
	default String generalDescription() {
		return tag().generalDescription();
	}
	
	default String description() {
		if(isInteger())
			return tag().description(integer());
		else
			return tag().description();
	}
	
	@Override
	default ActionSourceType type() {
		return ActionSourceType.MODIFIER;
	}
	
}
