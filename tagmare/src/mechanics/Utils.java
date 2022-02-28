package mechanics;

public final class Utils {

	private Utils() {
		
	}
	
	public static void takeDamage(int damage, Health health, Block block) {
		int blocked = Math.min(damage, block.amount());
		block.lose(blocked);
		damage -= blocked;
		if(damage > 0)
			health.lose(damage);
	}
	
}
