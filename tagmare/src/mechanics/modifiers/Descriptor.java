package mechanics.modifiers;

/** <p>An {@link Descriptor} provides a single function to generate an adequate description of what an
 * {@link Modifier} with a given {@link Modifier#integer() integer} is doing. Every {@link Descriptor} is
 * associated with exactly one kind modifier (that is, one {@link ModifierTag}), although it may not store a reference
 * to that tag.</p>
 * <p>If the associated modifier is not {@link ModifierTag#isIntegerModifier() an integer modifier},
 * {@link #forInteger(int)} returns a string with the same text every time regardless of the integer passed to it.</p>
 * */
@FunctionalInterface
public interface Descriptor {

	/** Returns a {@link Descriptor} whose {@link #forInteger(int)} method always returns the same string. */
	static Descriptor constant(String text) {
		return n -> text;
	}
	
	/** Returns a new {@link Descriptor} whose {@link #forInteger(int)} method returns {@code textFor1} if given
	 * {@code 1} and {@code descriptor.forInteger(n)} for any other integer {@code n}. */
	static Descriptor forked1(String textFor1, Descriptor descriptor) {
		return n -> n == 1 ? textFor1 : descriptor.forInteger(n);
	}
	
	/** Always returns a string with the same text if given the same integer. */
	String forInteger(int n);
	
}
