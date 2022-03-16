package mechanics.modifiers;

import static mechanics.modifiers.Descriptor.forked1;

import mechanics.modifiers.buffs.Discipline;

/** An immutable tag for each kind of {@link Modifier} (including hidden ones).*/
public enum ModifierTag {
	//All descriptions should be punctuated.
	AP_CLASSROOM("AP Classroom", "", "This is an AP Classroom assignment."),
	MOTIVATION("Motivation", n -> String.format("Your next attack deals %d extra damage.", n)),
	PROCRASTINATED("Procrastinated", "",
			n -> String.format("This enemy will take %d damage at the start of next turn.", n)),
	ENRAGED("Enraged", forked1("Immediately discard the next card you draw.",
			n -> String.format("Immediately discard the next %d cards you draw.", n))),
	KNOCKED_OUT("Knocked Out", "You cannot play any cards from your hand this turn."),
	DEFENESTRATING("Defenestrating", "GENERAL", n -> "IDUNNO" + n, true), //TODO test Defenestration
	NONSENSE("Nonsense", n -> String.format("Take %d damage at the end of your turn.", n)),
	TOMATOED("Tomatoed", "The next card you play this turn cannot be an attack and must not require more than 1 energy."),
	DISCIPLINE("Discipline", n -> String.format("Attacks deal %.0f%% more damage.", n * Discipline.PERCENT * 100)),
	ON_LEAVE("On Leave", n -> String.format("At the start of next turn, gain %d Concentration.", n)),
	CONCENTRATION("Concentration", n -> String.format("Gain %d more block from Skills.", n)),
	PLANNING_AHEAD("Planning Ahead", n -> String.format("At the start of next turn, gain %d block.", n)),
	TIRED("Tired", n -> String.format("Gain %d less energy at the start of next turn", n)),
	MEMORIZING("Memorizing", "You can no longer gain block from skills this turn."),
	RESERVES("Reserves", n -> String.format("At the start of next turn, gain %d energy.", n)),
	PACKED("Packed", forked1("At the start of next turn, gain 1 energy and draw 1 card.",
			n -> String.format("At the start of next turn, gain %d energy and draw %d cards.", n, n))),
	CHEATING("Cheating", forked1("Your next non-Copy card is played twice.",
			n -> String.format("Your next %d non-Copy cards are played twice", n))),
	TOXIC("Toxic", n -> String.format("At the end of your turn, take %d damage", n));
	
	private final String name, generalDescription;
	private final boolean isInteger;
	private final Descriptor descriptor;
	
	/** The general description can be an empty string or {@code null} if it is not needed. If {@code null}, it will
	 * be converted to an empty string. */
	ModifierTag(String name, String generalDescription, Descriptor descriptor, boolean isInteger) {
		this.name = name;
		this.isInteger = isInteger;
		this.generalDescription = generalDescription == null ? "" : generalDescription;
		this.descriptor = descriptor;
	}
	
	/** Assumes the modifier is an integer modifier. */
	ModifierTag(String name, String generalDescription, Descriptor descriptor) {
		this(name, generalDescription, descriptor, true);
	}
	
	/** Assumes the modifier is an integer modifier and sets the {@link #generalDescription()} to
	 * {@code descriptor.forInteger(1)}. */
	ModifierTag(String name, Descriptor descriptor) {
		this(name, descriptor.forInteger(1), descriptor);
	}
	
	/** Assumes the modifier is not an integer modifier. */
	ModifierTag(String name, String generalDescription, String constantDescription) {
		this(name, generalDescription, Descriptor.constant(constantDescription), false);
	}
	
	/** Assumes the modifier is not an integer modifier and sets both the {@link #generalDescription()} and
	 * {@link #description()} to {@code description}.*/
	ModifierTag(String name, String description) {
		this(name, description, description);
	}
	
	public String displayName() { //cannot call this "name()" because of the final method in Enum.
		return name;
	}
	
	public boolean isIntegerModifier() {
		return isInteger;
	}
	
	public String generalDescription() {
		return generalDescription;
	}
	
	/** Equivalent to {@code descriptor().forInteger(n)}.*/
	public String description(int n) {
		return descriptor().forInteger(n);
	}
	
	/** Should only be used if this is not an {@link #isIntegerModifier() integer modifier}.
	 * @throws UnsupportedOperationException if this tag represents an integer modifier. */
	public String description() {
		if(!isIntegerModifier())
			throw new UnsupportedOperationException(String.format("This is an integer modifier: %s", this));
		return description(0); //doesn't matter what we pass here.
	}
	
	public Descriptor descriptor() {
		return descriptor;
	}
	
}
