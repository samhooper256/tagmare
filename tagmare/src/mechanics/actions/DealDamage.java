package mechanics.actions;

import mechanics.*;

public class DealDamage extends AbstractTargettedAction {

	/** Must be non-negative. */
	private static int verifyDamage(int damage) {
		if(damage < 0)
			throw new IllegalArgumentException(String.format("Invalid amount of damage: %d", damage));
		return damage;
	}
	
	private int damage;
	
	public DealDamage(int damage, ActionSource source, Enemy target) {
		super(source, target);
		this.damage = verifyDamage(damage);
	}
	
	@Override
	public void execute() {
		target().setHealth(Math.max(0, target().health() - damage()));
	}
	
	public int damage() {
		return damage;
	}
	
	public void setAmount(int amount) {
		this.damage = verifyDamage(amount);
	}
	
}
