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
	
	public static <T> T last(List<T> list) {
		return list.get(list.size() - 1);
	}

	public static <T> T any(Collection<T> coll) {
		return coll.iterator().next();
	}
	
}
