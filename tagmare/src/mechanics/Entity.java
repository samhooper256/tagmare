package mechanics;

/** There are two kinds of {@link Entity Entities}: {@link Player} and {@link Enemy}. */
public interface Entity {

	int health();
	
	void setHealth(int health);
	
}
