package effects;

/** There are two kinds of {@link Effect Effects}: {@link Buff Buffs} and {@link Debuff Debuffs}. All
 * {@link #isInteger() integer} effects are mutable. */
public interface Effect {

	EffectTag tag();
	
	default boolean isBuff() {
		return this instanceof Buff;
	}
	
	default boolean isDebuff() {
		return this instanceof Debuff;
	}
	
	/** Visible {@link Effect Effects} will be shown to the user. */
	boolean isVisible();
	
	/** Hidden {@link Effect Effects} will not be shown to the user. */
	default boolean isHidden() {
		return !isVisible(); 
	}
	
	default String displayName() {
		return tag().displayName();
	}
	
	/** Returns {@code true} iff this {@link Effect} has an integer value associated with it. For example, see
	 * {@link Motivation}. */
	default boolean isInteger() {
		return tag().isIntegerEffect();
	}
	
	/** <p>Throws an {@link UnsupportedOperationException} if this {@link Effect} is not an {@link #isInteger() integer}
	 * effect.</p> 
	 * <p>The default implementation is written such that this method must only be overridden by
	 * integer {@code Effects}.</p> */
	default int integer() {
		if(isInteger())
			throw new UnsupportedOperationException("Must override");
		else
			throw new UnsupportedOperationException(
					String.format("This Effect (%s) is not an integer Effect", getClass().getSimpleName()));
	}
	
	/** <p>All {@link #isInteger() integer} {@link Effect Effects} are mutable and must support this method. Throws an
	 * {@link UnsupportedOperationException} iff this {@code Effect} is not an integer effect.</p>
	 * <p>This method simply updates the value of {@link #integer()}; it does not cause any downstream effects of that
	 * change to occur.</p>
	 * <p>The default implementation is written such that this method must only be overridden by integer
	 * {@code Effects}.</p> */
	default void setInteger(int integer) {
		if(isInteger())
			throw new UnsupportedOperationException("Must override");
		else
			throw new UnsupportedOperationException(
					String.format("This Effect (%s) is not an integer Effect", getClass().getSimpleName()));
	}
	
	default void increment() {
		setInteger(integer() + 1);
	}
	
	default void decrement() {
		setInteger(integer() - 1);
	}
	
}
