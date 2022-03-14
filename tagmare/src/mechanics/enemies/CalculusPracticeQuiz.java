package mechanics.enemies;

import mechanics.enemies.intents.*;

public class CalculusPracticeQuiz extends AbstractEnemy {

	public CalculusPracticeQuiz() {
		super(EnemyTag.CALCULUS_PRACTICE_QUIZ, 82);
	}

	@Override
	protected Intent generateIntent() {
		return new BlockStrike(99, 5);
	}
	
}
