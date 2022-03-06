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
}