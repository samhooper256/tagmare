package utils;

@FunctionalInterface
public interface CharPredicate {

	boolean testChar(char c);
	
	default CharPredicate negated() {
		return c -> !testChar(c);
	}
	
}
