package mechanics.actions;

import base.Entity;

public class DealDamage implements TargettedAction {

	/** Must be non-negative. */
	private static int verifyAmount(int amount) {
		if(amount < 0)
			throw new IllegalArgumentException(String.format("Invalid amount of damage: %d", amount));
		return amount;
	}
	
	private final int amount;
	private final ActionSource source;
	private final Entity target;
	
	public DealDamage(int amount, ActionSource source, Entity target) {
		this.amount = verifyAmount(amount);
		this.source = source;
		this.target = target;
	}
	
	public int amount() {
		return amount;
	}
	
	@Override
	public ActionSource source() {
		return source;
	}
	
	@Override
	public Entity target() {
		return target;
	}
	
}
