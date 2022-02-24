package mechanics.modifiers;

public class Motivation implements VisibleBuff {

	private int integer;
	
	/** Default {@link #integer()} value is {@code 1}. */
	public Motivation() {
		this(1);
	}
	
	public Motivation(int integer) {
		this.integer = integer;
	}
	
	
	@Override
	public int integer() {
		return integer;
	}
	
	@Override
	public void setInteger(int integer) {
		this.integer = integer;
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.MOTIVATION;
	}
	
}
