package mechanics;

import mechanics.cards.*;

/** A bunch of utility methods. */
public final class Hub {

	private static final GameInstance INSTANCE = new GameInstance(); //TODO this should not be here.
	//The purpose of Hub is to just have utility methods that access things from other classes. This class should not
	//itself be storing any important things.
	
	private Hub() {
		
	}
	
	public static GameInstance instance() {
		return INSTANCE;
	}
	
	public static Player player() {
		return instance().player();
	}
	
	public static Combat combat() {
		return instance().combat();
	}
	
	public static ActionStack stack() {
		return combat().stack();
	}
	
	public static Deck deck() {
		return player().deck();
	}
	
	public static DrawPile drawPile() {
		return combat().drawPile();
	}
	
	public static DiscardPile discardPile() {
		return combat().discardPile();
	}
	
	public static Hand hand() {
		return combat().hand();
	}
	
}
