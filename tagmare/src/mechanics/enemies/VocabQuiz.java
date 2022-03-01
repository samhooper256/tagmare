package mechanics.enemies;

import mechanics.Hub;
import mechanics.enemies.intents.*;

public class VocabQuiz extends AbstractEnemy {

	private static final int MAX_HEALTH = 4;
//	private static final int MAX_HEALTH = 24;
	
	public VocabQuiz() {
		super(MAX_HEALTH);
	}
	
	@Override
	protected Intent generateIntent() {
		if(Hub.turn() % 2 == 0)
			return new AttackIntent(11);
		else
			return new BlockIntent(7);
	}
	
	@Override
	public String name() {
		return "Vocab Quiz";
	}
	
}