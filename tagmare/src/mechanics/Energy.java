package mechanics;

public class Energy {

	public static final int MAX = 3;
	
	private int amount;
	
	public Energy() {
		amount = MAX;
	}

	public void setToMax() {
		amount = MAX;
	}
	
	public int amount() {
		return amount;
	}

	/** @throws IllegalArgumentException iff {@code (amount < 0)}.*/
	public void set(int amount) {
		if(amount < 0)
			throw new IllegalArgumentException(String.format("Cannot set energy to: %d", amount));
		this.amount = amount;
	}
	
	public void decrease(int amount) {
		set(this.amount - amount);
	}
	
	public void increase(int amount) {
		set(this.amount + amount);
	}
		
}
