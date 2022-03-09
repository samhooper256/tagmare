package utils;

public final class English {

	private English() {
		
	}
	
	public static String plural(String word, int amount) {
		return amount == 1 ? word : word + "s";
	}
	
}
