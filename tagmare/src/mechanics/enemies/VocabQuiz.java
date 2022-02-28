package mechanics.enemies;

import mechanics.enemies.intents.*;

public class VocabQuiz extends AbstractEnemy {

	private static final int MAX_HEALTH = 24;
	
	public VocabQuiz() {
		super(MAX_HEALTH);
	}
	
	@Override
	protected Intent generateIntent() {
		return new AttackIntent(this, 4);
	}
	
	@Override
	public String name() {
		return "Vocab Quiz";
	}
	
}