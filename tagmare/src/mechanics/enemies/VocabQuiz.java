package mechanics.enemies;

import mechanics.Hub;
import mechanics.enemies.intents.*;

public class VocabQuiz extends AbstractEnemy {

	public static final int MAX_HEALTH = 24, BLOCK = 7, DAMAGE = 11;
	
	public VocabQuiz() {
		super(EnemyTag.VOCAB_QUIZ, MAX_HEALTH);
	}
	
	@Override
	protected Intent generateIntent() {
		if(Hub.turn() % 2 == 0)
			return Intent.withParts(new AttackPart(DAMAGE));
		else
			return Intent.withParts(new BlockPart(BLOCK));
	}
	
}