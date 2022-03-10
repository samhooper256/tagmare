package mechanics.enemies;

import mechanics.enemies.intents.*;
import mechanics.modifiers.buffs.APClassroom;

public class APESProgressCheck extends AbstractEnemy {

	private static final int MAX_HEALTH = 29;
	
	public APESProgressCheck() {
		super(MAX_HEALTH);
		modifiers().add(new APClassroom());
	}
	
	@Override
	public String name() {
		return "APES Progress Check";
	}

	@Override
	protected Intent generateIntent() {
		return new StrikeIntent(6);
	}
	
}
