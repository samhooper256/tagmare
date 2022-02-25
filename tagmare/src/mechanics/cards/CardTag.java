package mechanics.cards;

import java.util.function.Supplier;

import mechanics.cards.attacks.DoHomework;

public enum CardTag {

	DO_HOMEWORK("Do Homework", true, DoHomework::new);
	
	private final String displayName;
	private final boolean targetted;
	private final Supplier<Card> supplier;
	
	CardTag(String displayName, boolean targetted, Supplier<Card> supplier) {
		this.displayName = displayName;
		this.targetted = targetted;
		this.supplier = supplier;
	}
	
	public String displayName() {
		return displayName;
	}
	
	public boolean isTargetted() {
		return targetted;
	}
	
	/** Generates a new {@link Card} every time. */
	public Card generate() {
		return supplier.get();
	}
	
}
