package utils;

public final class Strings {

	private Strings() {
		
	}

	public static boolean contains(String s, CharPredicate cp) {
		return indexOf(s, cp) >= 0;
	}
	
	public static int indexOf(String s, CharPredicate cp) {
		return indexOf(s, cp, 0, s.length());
	}

	/** {@code -1} if {@code startInclusive == endExclusive}. */
	public static int indexOf(String s, CharPredicate cp, int startInclusive, int endExclusive) {
		if(endExclusive > s.length() || startInclusive < 0 || startInclusive > endExclusive)
			throw new IllegalArgumentException(String.format("Invalid range: %d to %d", startInclusive, endExclusive));
		for(int i = startInclusive; i < endExclusive; i++)
			if(cp.testChar(s.charAt(i)))
				return i;
		return -1;
	}

	/** {@code true} if empty. */
	public static boolean containsOnly(String s, CharPredicate cp) {
		return containsOnly(s, cp, 0, s.length());
	}
	
	/** {@code true} if empty range ({@code startInclusive == endExclusive}). */
	public static boolean containsOnly(String s, CharPredicate cp, int startInclusive, int endExclusive) {
		return indexOf(s, cp.negated(), startInclusive, endExclusive) == -1;
	}
	
	public static boolean containsOnlyDigits(String s) {
		return containsOnlyDigits(s, 0);
	}
	
	public static boolean containsOnlyDigits(String s, int startInclusive) {
		return containsOnlyDigits(s, startInclusive, s.length());
	}
	
	public static boolean containsOnlyDigits(String s, int startInclusive, int endExclusive) {
		return containsOnly(s, Character::isDigit, startInclusive, endExclusive);
	}
	
	public static String repeat(String s, int times) {
		if(times < 0)
			throw new IllegalArgumentException(String.format("times < 0 (was %d)", times));
		StringBuilder result = new StringBuilder(s.length() * times);
		for(int i = 0; i < times; i++)
			result.append(s);
		return result.toString();
	}
	
}
