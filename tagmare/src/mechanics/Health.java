package mechanics;

public final class Health {

	private int max, hp;
	
	/** At max {@link #hp()} by default. */
	public Health(int max) {
		this(max, max);
	}
	
	public Health(int max, int hp) {
		setMax(max);
		setHP(hp);
	}
	
	public int max() {
		return max;
	}
	
	public int hp() {
		return hp;
	}
	
	/** Returns the proportion of the {@link #max() max hp} that is filled by the {@link #hp() hp}. */
	public double proportion() {
		return (double) hp() / max();
	}
	
	public void setMax(int max) {
		if(max <= 0)
			throw new IllegalArgumentException(String.format("max health is negative: %d", max));
		this.max = max;
		if(hp > max)
			hp = max;
	}
	
	public void setHP(int hp) {
		if(hp < 0 || hp > max)
			throw new IllegalArgumentException(String.format("Invalid hp: %d", hp));
		this.hp = hp;
	}

	/** Sets {@link #hp()} to {@code 0} if it would go negative. */
	public void lose(int hp) {
		gain(-hp);
	}
	
	/** Sets {@link #hp()} to {@link #max()} if it would go over. */
	public void gain(int hp) {
		setHP(Math.max(0, Math.min(max, this.hp + hp)));
	}
	
}
