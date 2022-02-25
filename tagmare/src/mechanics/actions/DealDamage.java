package mechanics.actions;

import mechanics.*;

public class DealDamage extends AbstractTargettedAction {

	/** Must be non-negative. */
	private static int verifyAmount(int amount) {
		if(amount < 0)
			throw new IllegalArgumentException(String.format("Invalid amount of damage: %d", amount));
		return amount;
	}
	
	private int damage;
	
	public DealDamage(int damage, ActionSource source, Enemy target) {
		super(source, target);
		this.damage = verifyAmount(damage);
	}
	
	@Override
	public void execute() {
		target().setHealth(Math.max(0, target().health() - damage()));
	}
	
	public int damage() {
		return damage;
	}
	
	public void setAmount(int amount) {
		this.damage = verifyAmount(amount);
	}
	
}
