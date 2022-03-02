package mechanics.cards;

import java.util.function.Supplier;

import mechanics.cards.attacks.*;
import mechanics.cards.skills.ReviewNotes;

public enum CardTag {
	DO_HOMEWORK("Do Homework", 1, true, DoHomework::new),
	REVIEW_NOTES("Review Notes", 1, false, ReviewNotes::new),
	PROCRASTINATE("Procrastinate", 1, true, Procrastinate::new),
	GRIND("Grind", 2, true, Grind::new),
	QUIZLET("Quizlet", 1, true, Quizlet::new),
	RAGE("Rage", 1, true, Rage::new),
	ALL_NIGHTER("All-Nighter", 2, false, AllNighter::new, true);
	
	private final String displayName;
	private final boolean targetted, oneTime;
	private final int energyCost;
	private final Supplier<Card> supplier;
	
	/** Not {@link #isOneTime() one-time}. */
	CardTag(String displayName, int energyCost, boolean targetted, Supplier<Card> supplier) {
		this(displayName, energyCost, targetted, supplier, false);
	}
	
	CardTag(String displayName, int energyCost, boolean targetted, Supplier<Card> supplier, boolean oneTime) {
		this.displayName = displayName;
		this.energyCost = energyCost;
		this.targetted = targetted;
		this.supplier = supplier;
		this.oneTime = oneTime;
	}
	
	public String displayName() {
		return displayName;
	}
	
	public int energyCost() {
		return energyCost;
	}
	
	public boolean isTargetted() {
		return targetted;
	}
	
	public boolean isOneTime() {
		return oneTime;
	}
	
	/** Generates a new {@link Card} every time. */
	public Card generate() {
		return supplier.get();
	}
	
}
