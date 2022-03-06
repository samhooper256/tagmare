package mechanics.actions;

import mechanics.Hub;
import mechanics.enemies.Enemy;

public class DealDamageToAll extends AbstractAction implements HasDamage {

	private int damage;
	
	public DealDamageToAll(ActionSource source, int damage) {
		super(source);
		this.damage = damage;
	}
	
	@Override
	public void execute() {
		for(Enemy e : Hub.enemies())
			e.takeDamage(damage());
	}
	
	@Override
	public boolean canExecute() {
		return !Hub.enemies().isEmpty();
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
