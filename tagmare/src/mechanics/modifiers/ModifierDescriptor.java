package mechanics.modifiers;

import mechanics.Descriptor;

/** <p>An {@link ModifierDescriptor} provides a single function to generate an adequate description of what an
 * {@link Modifier} with a given {@link Modifier#integer() integer} is doing. Every {@link ModifierDescriptor} is
 * associated with exactly one kind modifier (that is, one {@link ModifierTag}), although it may not store a reference
 * to that tag.</p>
 * <p>If the associated modifier is not {@link ModifierTag#isIntegerModifier() an integer modifier},
 * {@link #forInteger(int)} returns a string with the same text every time regardless of the integer passed to it.</p>
 * */
@FunctionalInterface
public interface ModifierDescriptor extends Descriptor {

	/** Returns a {@link ModifierDescriptor} whose {@link #forInteger(int)} method always returns the same string. */
	static ModifierDescriptor constant(String text) {
		return n -> text;
	}
	
	/** Returns a new {@link ModifierDescriptor} whose {@link #forInteger(int)} method returns {@code textFor1} if given
	 * {@code 1} and {@code descriptor.forInteger(n)} for any other integer {@code n}. */
	static ModifierDescriptor forked1(String textFor1, ModifierDescriptor descriptor) {
		return n -> n == 1 ? textFor1 : descriptor.forInteger(n);
	}
	
}
