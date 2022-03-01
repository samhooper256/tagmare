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
	ALL_NIGHTER("All-Nighter", 2, false, AllNighter::new);
	
	private final String displayName;
	private final boolean targetted;
	private final int energyCost;
	private final Supplier<Card> supplier;
	
	CardTag(String displayName, int energyCost, boolean targetted, Supplier<Card> supplier) {
		this.displayName = displayName;
		this.energyCost = energyCost;
		this.targetted = targetted;
		this.supplier = supplier;
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
	
	/** Generates a new {@link Card} every time. */
	public Card generate() {
		return supplier.get();
	}
	
}
