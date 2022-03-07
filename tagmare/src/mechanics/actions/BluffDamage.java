package mechanics.actions;

import mechanics.Hub;
import mechanics.enemies.Enemy;
import utils.RNG;

public class BluffDamage extends DealDamage {

	public BluffDamage(int damage, ActionSource source, Enemy target) {
		super(damage, source, target);
	}

	@Override
	public void execute() {
		super.execute();
		if(target().isAlive() && !Hub.hand().isEmpty())
			Hub.stack().push(new ExplicitDiscard(RNG.pickOrThrow(Hub.hand().cards()), null));
	}
	
}
