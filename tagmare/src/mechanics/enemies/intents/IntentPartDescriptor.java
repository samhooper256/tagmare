package mechanics.enemies.intents;

import mechanics.Descriptor;

/** <p>Generates a description for a certain kind of {@link IntentPart}. Each {@link IntentPartDescriptor} is associated
 * with exactly one kind of intent part (that is, one {@link IntentPartTag}).</p>
 * <p>For non-{@link IntentPartTag#isInteger() integer} intent parts, the parameter to {@link #forInteger(int)} is
 * ignored.</p> */
@FunctionalInterface
public interface IntentPartDescriptor extends Descriptor {

	static IntentPartDescriptor constant(String text) {
		return n -> text;
	}
	
}
