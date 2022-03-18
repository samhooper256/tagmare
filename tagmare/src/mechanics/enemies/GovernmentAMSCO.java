package mechanics.enemies;

import mechanics.Hub;
import mechanics.enemies.intents.*;
import mechanics.modifiers.buffs.Enlightened;
import mechanics.modifiers.debuffs.Tired;
import mechanics.modifiers.mixed.Motivation;

public class GovernmentAMSCO extends AbstractEnemy {

	public static final int MAX_HEALTH = 100;
	
	public GovernmentAMSCO() {
		super(EnemyTag.GOVERNMENT_AMSCO, MAX_HEALTH);
	}

	@Override
	protected Intent generateIntent() {
		if(Hub.turn() % 2 == 0)
			return Intent.withParts(new DebuffPart(new Tired(1)), new DebuffPart(new Motivation(-3)));
		else
			return Intent.withParts(new BuffPart(new Enlightened(1)));
	}
	
}
