package base;

import visuals.animations.Animation;

/** Provides the source of all update calls and the {@link Timer}.*/
public final class Updater {

	private Updater() {
		
	}

	private static final Timer TIMER = new Timer(Updater::update);
	
	public static Timer timer() {
		return TIMER;
	}

	public static void startTimer() {
		TIMER.start();
	}
	
	public static void update(long diff) {
		Animation.manager().update(diff);
	}
	
}
