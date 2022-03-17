package mechanics.enemies.intents;

import java.util.*;

/** Since this game is written in Java 8 to ensure maximum compatibility, we can't have private static methods in
 * interfaces (that was introduced in Java 9). This class contains methods that should all be 
 * {@code private static} methods of {@link Intent}. */
final class IntentStaticMethods {

	private IntentStaticMethods() {
		
	}
	
	/** The given {@link List} is <em>not</em> defensively copied; it is only made unmodifiable. The caller is trusted
	 * to never make any changes to the given list after passing it to this method. */
	static Intent withUnchangingParts(List<IntentPart> parts) {
		return withImmutableParts(Collections.unmodifiableList(parts));
	}
	
	/** The given {@link List} is trusted to be immutable. {@code parts} is directly returned unchanged by the
	 * {@link #parts()} method of the returned {@link Intent}. */
	static Intent withImmutableParts(List<IntentPart> parts) {
		return () -> parts;
	}
	
}
