package mechanics.actions;

import mechanics.Hub;

public class TakeDamage extends AbstractAction {

	private final int damage;
	
	public TakeDamage(int damage, ActionSource source) {
		super(source);
		this.damage = damage;
	}

	@Override
	public void execute() {
		Hub.player().takeDamage(damage());
	}
	
	public int damage() {
		return damage;
	}
	
}
