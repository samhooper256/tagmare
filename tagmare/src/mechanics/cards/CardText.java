package mechanics.cards;

import java.util.*;

import mechanics.effects.*;
import utils.Strings;

public final class CardText {

	private static final List<String> CUSTOM_NAMES = Collections.unmodifiableList(Arrays.asList("W", "X", "Y", "Z"));
	
	private static String DString(int num) {
		return String.format("D%d", num);
	}
	
	private static String BString(int num) {
		return String.format("B%d", num);
	}
	
	//Defaults are unmodifiable.
	private final List<Integer> defaultDamages, damages;
	private final List<Integer> defaultBlocks, blocks;
	private final List<Integer> defaultCustoms, customs;
	private final String formattedString;
	
	/** Use the variables D0, D1, D2, etc. to denote damage amounts that can change. Use the variables W, X, Y, and Z to
	 * denote custom variables (these variables will not be changed by {@link #update(Card)}; they will only change if
	 * {@link #set(String, int)}). You must introduce each custom variable in alphabetical order, although you may
	 * refer to the same variable more than once. */
	public CardText(String formattedString, int... defaultValuesOfVariables) {
		this.formattedString = formattedString;
		List<Integer> defaultDamagesMutable = new ArrayList<>(),
				defaultCustomsMutable = new ArrayList<>(), defaultBlocksMutable = new ArrayList<>();
		String[] words = formattedString.split(" ");
		int damageIndex = 0, blockIndex = 0, customIndex = 0, vi = 0;
		for(String s : words) {
			if(s.isEmpty())
				continue;
			if(s.charAt(0) == 'D' && Strings.containsOnlyDigits(s, 1)) {
				int num = Integer.parseInt(s.substring(1));
				if(num < damageIndex) //additional reference to a damage variable - fine.
					continue;
				if(num != damageIndex)
					throw new IllegalArgumentException(String.format("Expected D%d, found %s", damageIndex, s));
				damageIndex++;
				defaultDamagesMutable.add(defaultValuesOfVariables[vi++]);
			}
			else if(s.charAt(0) == 'B' && Strings.containsOnlyDigits(s, 1)) {
				int num = Integer.parseInt(s.substring(1));
				if(num < blockIndex) //additional reference to a block varible - fine.
					continue;
				if(num != blockIndex)
					throw new IllegalArgumentException(String.format("Expected B%d, found %s", blockIndex, s));
				blockIndex++;
				defaultBlocksMutable.add(defaultValuesOfVariables[vi++]);
			}
			else if(CUSTOM_NAMES.contains(s)) {
				int i = CUSTOM_NAMES.indexOf(s);
				if(i < customIndex) //additional reference to a custom variable - fine.
					continue;
				if(CUSTOM_NAMES.indexOf(s) > customIndex)
					throw new IllegalArgumentException("Invalid custom variable order. Must be W, X, Y, Z");
				customIndex++;
				defaultCustomsMutable.add(defaultValuesOfVariables[vi++]);
			}
		}
		if(vi != defaultValuesOfVariables.length)
			throw new IllegalArgumentException(String.format("Invalid number of variables. Variables passed: %s", 
					Arrays.toString(defaultValuesOfVariables)));
		defaultDamages = Collections.unmodifiableList(defaultDamagesMutable);
		defaultCustoms = Collections.unmodifiableList(defaultCustomsMutable);
		defaultBlocks = Collections.unmodifiableList(defaultBlocksMutable);
		damages = new ArrayList<>(defaultDamages);
		customs = new ArrayList<>(defaultCustoms);
		blocks = new ArrayList<>(defaultBlocks);
	}
	
	/** Makes copy for {@link #damages}. */
	private CardText(String formattedString, List<Integer> defaultDamages, List<Integer> defaultCustoms,
			List<Integer> defaultBlocks) {
		this.formattedString = formattedString;
		this.defaultDamages = defaultDamages;
		this.damages = new ArrayList<>(defaultDamages);
		this.defaultCustoms = defaultCustoms;
		this.customs = new ArrayList<>(defaultCustoms);
		this.defaultBlocks = defaultBlocks;
		this.blocks = new ArrayList<>(defaultBlocks);
	}
	
	public String defaultText() {
		return getText(defaultDamages, defaultBlocks, defaultCustoms);
	}
	
	public String displayText() {
		return getText(damages, blocks, customs);
	}
	
	private String getText(List<Integer> damages, List<Integer> blocks, List<Integer> customs) {
		String text = formattedString;
		for(int i = 0; i < damages.size(); i++)
			text = text.replace(DString(i), String.valueOf(damages.get(i)));
		for(int i = 0; i < blocks.size(); i++)
			text = text.replace(BString(i), String.valueOf(blocks.get(i)));
		for(int i = 0; i < customs.size(); i++)
			text = text.replace(CUSTOM_NAMES.get(i), String.valueOf(customs.get(i)));
		return text;
	}
	
	public String formattedString() {
		return formattedString;
	}
	
	public CardText copy() {
		return new CardText(formattedString, defaultDamages, defaultCustoms, defaultBlocks);
	}

	public void set(String variable, int newValue) {
		customs.set(CUSTOM_NAMES.indexOf(variable), newValue);
	}
	
	public int D(int num) {
		return damages.get(num);
	}
	
	public int defaultD(int num) {
		return defaultDamages.get(num);
	}

	public int Dcount() {
		return defaultDamages.size();
	}
	
	public int B(int num) {
		return blocks.get(num);
	}
	
	public int defaultB(int num) {
		return defaultBlocks.get(num);
	}

	public int Bcount() {
		return blocks.size();
	}
	
	public int get(String variable) {
		if(CUSTOM_NAMES.contains(variable))
			return customs.get(CUSTOM_NAMES.indexOf(variable));
		if(variable.charAt(0) == 'D')
			return D(Integer.parseInt(variable.substring(1)));
		throw new IllegalArgumentException(String.format("variable not found: %s", variable));
	}
	
	public void update(Card card) {
		if(card instanceof Attack)
			for(int i = 0; i < Dcount(); i++)
				damages.set(i, AttackEffects.getModifiedDamage((Attack) card, defaultDamages.get(i)));
		if(card instanceof Skill)
			for(int i = 0; i < Bcount(); i++)
				blocks.set(i, SkillEffects.getModifiedBlock((Skill) card, defaultBlocks.get(i)));
	}
	
}
