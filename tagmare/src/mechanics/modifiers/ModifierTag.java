package mechanics.modifiers;

/** An immutable tag for each kind of {@link Modifier} (including hidden ones).*/
public enum ModifierTag {
	AP_CLASSROOM("AP Classroom", false),
	MOTIVATION("Motivation", true),
	PROCRASTINATED("Procrastinated", true),
	ENRAGED("Enraged", true),
	KNOCKED_OUT("Knocked Out", false),
	DEFENESTRATING("Defenestrating", true),
	NONSENSE("Nonsense", true),
	TOMATOED("Tomatoed", false),
	DISCIPLINE("Discipline", true),
	ON_LEAVE("On Leave", true),
	CONCENTRATION("Concentration", true),
	PLANNING_AHEAD("Planning Ahead", true);
	
	private final String name;
	private final boolean isInteger;
	
	ModifierTag(String name, boolean isInteger) {
		this.name = name;
		this.isInteger = isInteger;
	}
	
	public String displayName() { //cannot call this "name()" because of the final method in Enum.
		return name;
	}
	
	public boolean isIntegerModifier() {
		return isInteger;
	}
	
}
