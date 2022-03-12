package utils;

import java.util.*;

public final class RNG {

	private RNG() {
		
	}
	
	public static final Random SOURCE = new Random();
	
	public static int intExclusive(int highExclusive) {
		return SOURCE.nextInt(highExclusive);
	}
	
	public static <T> T pickOrThrow(List<T> list) {
		if(list.isEmpty())
			throw new IllegalArgumentException("Empty list");
		return list.get(intExclusive(list.size()));
	}
	
	/** Returned {@link List} is modifiable; modifications will not affect the given list. */
	public static <T> List<T> perm(List<T> list) {
		ArrayList<T> perm = new ArrayList<>(list);
		Collections.shuffle(perm, SOURCE);
		return perm;
	}
	
}