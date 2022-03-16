package mechanics.enemies;

import mechanics.Hub;
import mechanics.enemies.intents.*;

public class VocabQuiz extends AbstractEnemy {

	private static final int MAX_HEALTH = 24_000;
	
	public VocabQuiz() {
		super(EnemyTag.VOCAB_QUIZ, MAX_HEALTH);
	}
	
	@Override
	protected Intent generateIntent() {
		if(Hub.turn() % 2 == 0)
			return new StrikeIntent(11);
		else
			return new BasicBlock(7);
	}
	
}