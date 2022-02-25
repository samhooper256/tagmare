package utils;

import java.util.*;

public final class Colls {

	private Colls() {
		
	}
	
	/** Returns an unmodifiable {@link List}. */
	@SuppressWarnings("unchecked")
	public static <T> List<T> ulist(T... items) {
		return Collections.unmodifiableList(Arrays.asList(items));
	}
	
}
