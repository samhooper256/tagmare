package mechanics.enemies.intents;

/** A single piece of damage dealt from an enemy. Damage dealt by a {@link Strike} happens in a single hit. This class
 * is mutable. */
public final class Strike {
	
	private int damage;
	
	public Strike(int damage) {
		this.damage = damage;
	}

	public int damage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public String toString() {
		return String.format("Strike[%d]", damage());
	}
	
}
