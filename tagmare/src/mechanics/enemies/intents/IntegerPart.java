package mechanics.enemies.intents;

public abstract class IntegerPart implements IntentPart {

	private final int integer;
	
	public IntegerPart(int integer) {
		this.integer = integer;
	}
	
	@Override
	public int integer() {
		return integer;
	}
	
}
