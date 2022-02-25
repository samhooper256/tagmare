package mechanics.modifiers;

import java.util.function.Supplier;

/** An immutable tag for each kind of {@link Modifier} (including hidden ones).*/
public enum ModifierTag {
	MOTIVATION("Motivation", true, Motivation::new);
	
	private final String name;
	private final boolean isInteger;
	private final Supplier<? extends Modifier> supplier;
	
	ModifierTag(String name, boolean isInteger, Supplier<? extends Modifier> supplier) {
		this.name = name;
		this.isInteger = isInteger;
		this.supplier = supplier;
	}
	
	public String displayName() { //cannot call this "name()" because of the final method in Enum.
		return name;
	}
	
	public boolean isIntegerModifier() {
		return isInteger;
	}
	
	/** <p>If this {@link ModifierTag} describes an {@link #isIntegerModifier() integer} {@link Modifier}, the generated
	 * {@code Modifier} has a suitable default {@link Modifier#integer()} value.</p>
	 * <p>Returns a new object every time.</p>
	 */
	public Modifier generate() {
		return supplier.get();
	}
	
}
