package effects;

import java.util.function.Supplier;

/** An immutable tag for each kind of {@link Effect} (including hidden ones).*/
public enum EffectTag {
	MOTIVATION("Motivation", true, Motivation::new);
	
	private final String name;
	private final boolean isInteger;
	private final Supplier<? extends Effect> supplier;
	
	private EffectTag(String name, boolean isInteger, Supplier<? extends Effect> supplier) {
		this.name = name;
		this.isInteger = isInteger;
		this.supplier = supplier;
	}
	
	public String displayName() { //cannot call this "name()" because of the final method in Enum.
		return name;
	}
	
	public boolean isIntegerEffect() {
		return isInteger;
	}
	
	/** <p>If this {@link EffectTag} describes an {@link #isIntegerEffect() integer} {@link Effect}, the generated
	 * {@code Effect} has a suitable default {@link Effect#integer()} value.</p>
	 * <p>Returns a new object every time.</p>
	 */
	public Effect generate() {
		return supplier.get();
	}
	
}
