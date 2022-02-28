package mechanics.modifiers;

abstract class IntegerModifier implements Modifier {
	
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
	
}
