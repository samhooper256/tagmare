package mechanics.enemies;

import mechanics.enemies.intents.*;

public class CalculusPracticeQuiz extends AbstractEnemy {

	public CalculusPracticeQuiz() {
		super(82);
	}

	@Override
	public String name() {
		return "Calculus Practice Quiz";
	}

	@Override
	protected Intent generateIntent() {
		return new BlockStrike(5, 5);
	}
	
}
