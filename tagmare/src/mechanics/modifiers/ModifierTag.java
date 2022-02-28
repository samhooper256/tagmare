package mechanics.modifiers;

/** An immutable tag for each kind of {@link Modifier} (including hidden ones).*/
public enum ModifierTag {
	MOTIVATION("Motivation", true),
	PROCRASTINATED("Procrastinated", true),
	AP_CLASSROOM("AP Classroom", true);
	
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
