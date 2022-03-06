package mechanics.cards;

import java.util.*;

public class CardText {

	private static final List<String> VARIABLE_LETTERS =
			Collections.unmodifiableList(Arrays.asList("W", "X", "Y", "Z"));
	
	private final Map<String, Integer> defaultValueMap;
	private final String formattedString;
	
	/** Use the capital letters W, X, Y, and Z IN ORDER to denote variables.
	 * Put the values in the same order as the variables. */
	public CardText(String formattedString, int... defaultValuesOfVariables) {
		if(defaultValuesOfVariables.length > VARIABLE_LETTERS.size())
			throw new IllegalArgumentException(
					String.format("Too many variables. Default values: %s", Arrays.toString(defaultValuesOfVariables)));
		this.formattedString = formattedString;
		defaultValueMap = new LinkedHashMap<>();
		for(int i = 0; i < defaultValuesOfVariables.length; i++)
			defaultValueMap.put(VARIABLE_LETTERS.get(i), defaultValuesOfVariables[i]);
	}
	
	public String defaultText() {
		String text = formattedString;
		for(Map.Entry<String, Integer> e : defaultValueMap.entrySet())
			text = text.replace(e.getKey(), String.valueOf(e.getValue()));
		return text;
	}
	
	public String formattedString() {
		return formattedString;
	}
	
}
