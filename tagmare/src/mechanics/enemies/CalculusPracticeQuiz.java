package mechanics.enemies;

import mechanics.enemies.intents.*;

public class CalculusPracticeQuiz extends AbstractEnemy {

	public static final int MAX_HEALTH = 82, BLOCK = 99, DAMAGE = 5;
	
	public CalculusPracticeQuiz() {
		super(EnemyTag.CALCULUS_PRACTICE_QUIZ, MAX_HEALTH);
	}

	@Override
	protected Intent generateIntent() {
		return Intent.withParts(new BlockPart(BLOCK), new AttackPart(DAMAGE));
	}
	
}
