package mechanics;

import mechanics.modifiers.Modifier;

/** A {@link Descriptor} provides a single function to generate an adequate description of something (such as a
 * {@link Modifier}) that may or may not have an integer associated with it. */
public interface Descriptor {

	/** Always returns a string with the same text if given the same integer. */
	String forInteger(int n);
	
}
