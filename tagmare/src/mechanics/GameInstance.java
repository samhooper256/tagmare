package mechanics;

public class GameInstance {

	private final Player player;
	private final Combat combat;
	
	public GameInstance() {
		this.player = new Player();
		combat = new Combat();
	}
	
	public Player player() {
		return player;
	}
	
	/** Returns the current {@link Combat} the {@link Player} is in or {@code null} if the player is not in combat. */
	public Combat combat() {
		return combat;
	}
	
}
