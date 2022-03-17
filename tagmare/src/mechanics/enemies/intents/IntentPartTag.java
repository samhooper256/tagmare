package mechanics.enemies.intents;

import static mechanics.enemies.intents.IntentPartDescriptor.constant;

public enum IntentPartTag {
	//Descriptions should be punctuated.
	ATTACK_PART(true, n -> String.format("This enemy intends to attack for %d damage.", n)),
	BLOCK_PART(true, n -> String.format("This enemy intends to gain %d block.", n)),
	BUFF_PART(false, constant("This enemy intends to buff itself.")),
	DEBUFF_PART(false, constant("This enemy intends to debuff you."));
	
	private final boolean isInteger;
	private final IntentPartDescriptor descriptor;
	
	IntentPartTag(boolean isInteger, IntentPartDescriptor descriptor) {
		this.isInteger = isInteger;
		this.descriptor = descriptor;
	}

	/** Parameter ignored if not {@link #isInteger()}. */
	public String description(int integer) {
		return descriptor().forInteger(integer);
	}

	public boolean isInteger() {
		return isInteger;
	}
	
	public IntentPartDescriptor descriptor() {
		return descriptor;
	}
	
}
