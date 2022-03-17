package mechanics.enemies;

import mechanics.enemies.intents.*;
import mechanics.modifiers.buffs.APClassroom;

public class APESProgressCheck extends AbstractEnemy {

	private static final int MAX_HEALTH = 29, DAMAGE = 6;
	
	public APESProgressCheck() {
		super(EnemyTag.APES_PROGRESS_CHECK, MAX_HEALTH);
		modifiers().add(new APClassroom());
	}

	@Override
	protected Intent generateIntent() {
		return Intent.withParts(new AttackPart(DAMAGE));
	}
	
}
