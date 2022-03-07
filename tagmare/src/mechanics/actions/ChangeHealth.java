package mechanics.actions;

import mechanics.Entity;

/** Changes the target's {@link Entity#health()} by the given positive or negative amount. */
public class ChangeHealth extends AbstractTargettedAction {

	private int amount;
	
	public ChangeHealth(int amount, ActionSource source, Entity target) {
		super(source, target);
		this.amount = amount;
	}
	
	public int amount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount =  amount;
	}

	@Override
	public void execute() {
		target().health().gain(amount());
	}
	
}
