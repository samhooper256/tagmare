package mechanics.enemies;

public class VocabQuiz extends AbstractEnemy {

	private static final int MAX_HEALTH = 24;
	
	public VocabQuiz() {
		super(MAX_HEALTH);
	}
	
	@Override
	public String name() {
		return "Vocab Quiz";
	}
	
}