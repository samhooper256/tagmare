package mechanics;

public class GameInstance {

	private final Player player;
	private final Calendar calendar;
	
	private boolean inCombat;
	
	public GameInstance() {
		this.player = new Player();
		calendar = new Calendar();
	}
	
	public Player player() {
		return player;
	}
	
	public Calendar calendar() {
		return calendar;
	}
	
	/** Returns {@code null} if no current combat. */
	public Combat combat() {
		if(isInCombat())
			return calendar().combat();
		return null;
	}

	public boolean isInCombat() {
		return inCombat;
	}
	
	/** @throws IllegalStateException if {@link #isInCombat()}. */
	public void moveToInCombat() {
		if(isInCombat())
			throw new IllegalStateException(String.format("This GameInstance is in combat: %s", combat()));
		inCombat = true;
	}
	
}
