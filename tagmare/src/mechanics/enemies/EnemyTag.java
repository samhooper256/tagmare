package mechanics.enemies;

public enum EnemyTag {
	VOCAB_QUIZ("Vocab Quiz"),
	APES_PROGRESS_CHECK("APES Progress Check"),
	CALCULUS_PRACTICE_QUIZ("Calculus Practice Quiz"),
	GOVERNMENT_AMSCO("Government AMSCO");
	
	private final String displayName;
	
	EnemyTag(String displayName) {
		this.displayName = displayName;
	}
	
	public String displayName() {
		return displayName;
	}
	
}
