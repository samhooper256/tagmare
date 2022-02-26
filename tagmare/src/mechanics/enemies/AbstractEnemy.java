package mechanics.enemies;

import mechanics.Health;

abstract class AbstractEnemy implements Enemy {

	private final Health health;
	
	protected AbstractEnemy(int maxHealth) {
		health = new Health(maxHealth);
	}
	
	@Override
	public Health health() {
		return health;
	}
	
}
