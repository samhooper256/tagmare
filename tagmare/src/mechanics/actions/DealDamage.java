package mechanics.actions;

import mechanics.enemies.Enemy;

public class DealDamage extends AbstractTargettedAction implements HasDamage {

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
		target().takeDamage(damage);
	}
	
	@Override
	public int damage() {
		return damage;
	}
	
	@Override
	public void setDamage(int amount) {
		this.damage = verifyDamage(amount);
	}
	
	@Override
	public Enemy target() {
		return (Enemy) super.target();
	}
	
}
