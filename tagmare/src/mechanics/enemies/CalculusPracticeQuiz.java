package mechanics.enemies;

import mechanics.enemies.intents.*;

public class CalculusPracticeQuiz extends AbstractEnemy {

	public static final int BLOCK = 99, DAMAGE = 5;
	public CalculusPracticeQuiz() {
		super(EnemyTag.CALCULUS_PRACTICE_QUIZ, 82);
	}

	@Override
	protected Intent generateIntent() {
		return Intent.withParts(new BlockPart(BLOCK), new AttackPart(DAMAGE));
	}
	
}
