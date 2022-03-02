package visuals.animations;

import base.Updatable;

public interface Animation extends Updatable {

	/** Equivalent to {@link AnimationManager#get()}. */
	static AnimationManager manager() {
		return AnimationManager.get();
	}
	
	void interpolate(double frac);
	
	void start();
	
	void restart();
	
	void pause();
	
	void unpause();
	
	FinisherAnimation withFinisher(Runnable finisher);
	
	boolean isRunning();
	
	boolean isFinished();
	
	boolean isPaused();
	
	long duration();
	
}
