package visuals.animations;

import base.Updatable;

public interface Animation extends Updatable {

	/** Equivalent to {@link AnimationManager#get()}. */
	static AnimationManager manager() {
		return AnimationManager.get();
	}
	
	/** The {@code double} passed here is <em><strong>AFTER</strong></em> having the {@link #interpolator()} applied
	 * to it. Will always be called with {@code 1.0} for any given playthrough of this {@link AbstractAnimation}. */
	void interpolate(double frac);
	
	void start();
	
	void restart();
	
	void pause();
	
	void unpause();
	
	boolean isRunning();
	
	boolean isFinished();
	
	boolean isPaused();
	
	long duration();
	
	Interpolator interpolator();
	
	double rate();
	
	void setRate(double rate);
	
	Runnable finishAction();
	
	/** Returns {@code this}. */
	Animation setFinish(Runnable onFinish);
	
	/** An action that will be played when the animation is playing with a negative {@link #rate()} and reaches the
	 * start of the animation. */
	Runnable reverseFinishAction();
	
	/** Returns {@code this}. */
	Animation setReverseFinish(Runnable reverseFinish);

}
