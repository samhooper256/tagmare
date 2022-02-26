package base.animations;

import base.Updatable;

/** Animations are not {@link #isPaused() paused} by default. Any {@link #update(long)} calls will cause the
 * {@link Animation} to progress. */
public abstract class Animation implements Updatable {

	/** Equivalent to {@link AnimationManager#get()}. */
	public static AnimationManager manager() {
		return AnimationManager.get();
	}
	
	private final long duration;
	
	private long elapsed;
	private boolean paused;
	
	public Animation(long duration) {
		this.duration = duration;
		elapsed = 0;
		paused = false;
	}
	
	/** Does nothing if {@link #isPaused()}. */
	@Override
	public final void update(long diff) {
		if(paused)
			return;
		elapsed += diff;
		if(elapsed >= duration)
			interpolate(1);
		else
			interpolate(progress());
	}
	
	private double progress() {
		return 1d * elapsed / duration;
	}
	
	/** Will always be called with {@code 0.0} and {@code 1.0} for any given playthrough of this {@link Animation}. */
	public abstract void interpolate(double frac);
	
	/** @throws IllegalStateException if {@link #isRunning()}. See {@link #restart()}. */
	public void start() {
		if(isRunning())
			throw new IllegalStateException("Running");
		restart();
	}
	
	/** Restarts and unpauses this animation at the beginning, whether or not it is currently
	 * {@link #isRunning() running}.*/
	public void restart() {
		elapsed = 0;
		paused = false;
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unpause() {
		paused = false;
	}
	
	/** Returns a <em>new</em> {@link FinisherAnimation} that its equivalent to this {@link Animation} but with the
	 * given {@link FinisherAnimation#finisher() finisher}. The given finisher is played after the finisher for this
	 * animation, if any. */
	public FinisherAnimation withFinisher(Runnable finisher) {
		return new AnimationWithFinisher(this, finisher);
	}

	public boolean isRunning() {
		return !isPaused() && elapsed >= 0 && !isFinished();
	}
	
	public boolean isFinished() {
		return elapsed >= duration;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	/** in nanos. */
	public long duration() {
		return duration;
	}
	
}
