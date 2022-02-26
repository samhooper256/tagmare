package mechanics.player;

public class Block {

	private int amount;
	
	public Block() {
		amount = 0;
	}
	
	public int amount() {
		return amount;
	}
	
	public void set(int amount) {
		if(amount < 0)
			throw new IllegalArgumentException(String.format("Invalid block amount: %d", amount));
		this.amount = amount;
	}
	
	public void gain(int amount) {
		this.amount += amount;
	}
	
	public void lose(int amount) {
		this.amount = Math.max(0, this.amount - amount);
	}

}
