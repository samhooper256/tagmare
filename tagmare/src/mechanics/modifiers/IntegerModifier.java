package mechanics.modifiers;

abstract class IntegerModifier extends AbstractModifier {
	
	private int integer;
	
	public IntegerModifier(int integer) {
		this.integer = integer;
	}
	
	@Override
	public final int integer() {
		return integer;
	}
	
	@Override
	public final void setInteger(int integer) {
		this.integer = integer;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%d)", getClass().getSimpleName(), integer());
	}
	
}
