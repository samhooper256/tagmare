package utils;

public final class English {

	private English() {
		
	}
	
	/** {@code word} is the singular form of the word (e.g. "card" not "cards"). */
	public static String plural(String word, int amount) {
		return amount == 1 ? word : word + "s";
	}
	
	/** Returns a string containing the {@code amount}, a space, then the correct plural form of {@code word}. */
	public static String pluralSpaced(String word, int amount) {
		return amount + " " + plural(word, amount);
	}
	
}
