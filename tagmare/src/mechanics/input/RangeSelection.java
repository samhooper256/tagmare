package mechanics.input;

import java.util.*;

/** A {@link CardSelection} that only {@link #validate(List) validates} {@link List Lists} of cards that have
 * between {@link #min()} and {@link #max()} elements (inclusive). The {@link #validate(List)} method may impose
 * additional constraints, but it must return {@code false} if there are fewer than {@code min()} or greater than
 * {@code max()} cards. */
public interface RangeSelection extends CardSelection {

	int min();
	
	int max();
	
}
