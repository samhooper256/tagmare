package utils;

import java.util.*;
import java.util.stream.IntStream;

public final class RNG {

	private RNG() {
		
	}
	
	public static final Random SOURCE = new Random();
	
	public static int intExclusive(int highExclusive) {
		return SOURCE.nextInt(highExclusive);
	}
	
	/** Returns a random element from the given {@link List}.
	 * @throws IllegalAccessException if empty. */
	public static <T> T pickOrThrow(List<T> list) {
		if(list.isEmpty())
			throw new IllegalArgumentException("Empty list");
		return list.get(intExclusive(list.size()));
	}
	
	/** Returns a {@link List} containing {@code k} elements from {@code k} unique indices in the given list. The
	 * returned list is modifiable; changes are not reflected in the given list.
	 * @throws IllegalArgumentException if {@code (k < 0 || k > list.size())}. */
	public static <T> List<T> pickUnique(List<T> list, int k) {
		if(k < 0 || k > list.size())
			throw new IllegalArgumentException(
			String.format("Cannot pick %d elements from a List of size %d.", k, list.size()));
		if(k == 0)
			return new ArrayList<>(0);
		List<T> picks = new ArrayList<>(k);
		int[] nums = IntStream.range(0, list.size()).toArray();
		int maxIndex = nums.length;
		for(int i = 0; i < k; i++) {
			int ni = intExclusive(maxIndex);
			maxIndex--;
			picks.add(list.get(nums[ni]));
			Arrs.swap(nums, ni, maxIndex);
		}
		return picks;
	}
	
	public static <T> List<T> pickUnique(T[] arr, int k) {
		return pickUnique(Arrays.asList(arr), k);
	}
	
	/** Returned {@link List} is modifiable; modifications will not affect the given list. */
	public static <T> List<T> perm(List<T> list) {
		ArrayList<T> perm = new ArrayList<>(list);
		Collections.shuffle(perm, SOURCE);
		return perm;
	}
	
}