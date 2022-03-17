package mechanics.actions;

import mechanics.Hub;

public class TakeDamage extends AbstractAction implements HasDamage {

	private int damage;
	
	public TakeDamage(int damage, ActionSource source) {
		super(source);
		this.damage = damage;
	}

	@Override
	public void execute() {
		Hub.player().takeDamage(damage());
	}
	
	@Override
	public int damage() {
		return damage;
	}
	
	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
