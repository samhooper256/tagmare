package mechanics.cards;

import java.util.function.Supplier;

import mechanics.cards.attacks.*;

public enum CardTag {
	DO_HOMEWORK("Do Homework", 1, true, DoHomework::new),
	GRIND("Grind", 2, true, Grind::new);
	
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
