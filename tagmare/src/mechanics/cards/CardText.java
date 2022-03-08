package mechanics.cards;

import java.util.*;

import mechanics.effects.AttackEffects;
import utils.Strings;

public final class CardText {

	private static String DString(int num) {
		return String.format("D%d", num);
	}
	
	private final List<Integer> defaultDamages, damages;
	private final String formattedString;
	
	/** Use the variables D1, D2, etc. to denote damage amounts that can change. */
	public CardText(String formattedString, int... defaultValuesOfVariables) {
		this.formattedString = formattedString;
		defaultDamages = new ArrayList<>();
		damages = new ArrayList<>();
		String[] words = formattedString.split(" ");
		int damageIndex = 1, vi = 0;
		for(String s : words) {
			if(!s.isEmpty() && s.charAt(0) == 'D' && Strings.containsOnlyDigits(s, 1)) {
				int num = Integer.parseInt(s.substring(1));
				if(num != damageIndex)
					throw new IllegalArgumentException(String.format("Expected D%d, found %s", damageIndex, s));
				damageIndex++;
				defaultDamages.add(defaultValuesOfVariables[vi++]);
			}
		}
	}
	
	/** Makes copy for both {@link #defaultDamages} and {@link #damages}. */
	private CardText(String formattedString, List<Integer> defaultDamages) {
		this.formattedString = formattedString;
		this.defaultDamages = defaultDamages;
		this.damages = new ArrayList<>(defaultDamages);
	}
	
	public String defaultText() {
		return getText(defaultDamages);
	}
	
	public String displayText() {
		return getText(damages);
	}
	
	private String getText(List<Integer> damages) {
		String text = formattedString;
		for(int i = 0; i < damages.size(); i++)
			text = text.replace(DString(i + 1), String.valueOf(damages.get(i)));
		return text;
	}
	
	public String formattedString() {
		return formattedString;
	}
	
	public CardText copy() {
		return new CardText(formattedString, defaultDamages);
	}

	public int D(int num) {
		return damages.get(num - 1);
	}
	
	public int defaultD(int num) {
		return defaultDamages.get(num - 1);
	}

	public int Dcount() {
		return defaultDamages.size();
	}
	
	public void update(Card card) {
		if(card instanceof Attack)
			for(int i = 0; i < Dcount(); i++)
				damages.set(i, AttackEffects.getModifiedDamage((Attack) card, defaultDamages.get(i)));
	}
	
}
