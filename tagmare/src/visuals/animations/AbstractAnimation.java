package visuals.animations;

import javafx.util.Duration;

/** Animations are not {@link #isPaused() paused} by default. Any {@link #update(long)} calls will cause the
 * {@link AbstractAnimation} to progress. */
public abstract class AbstractAnimation implements Animation {

	private static long asNanos(Duration duration) {
		if(duration.isIndefinite() || duration.isUnknown())
			throw new IllegalArgumentException(String.format("Invalid duration: %s", duration));
		return (long) (duration.toMillis() * 1e6);
	}
	
	private final long duration;
	
	private long elapsed;
	private boolean paused;
	
	public AbstractAnimation(Duration duration) {
		this(asNanos(duration));
	}
	
	/** @param duration in nanos. */
	public AbstractAnimation(long duration) {
		this.duration = duration;
		elapsed = 0;
		paused = false;
	}
	
	/** Does nothing if {@link #isPaused()} or {@link #isFinished()}. */
	@Override
	public final void update(long diff) {
		if(isPaused() || isFinished())
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
	
	/** Will always be called with {@code 0.0} and {@code 1.0} for any given playthrough of this {@link AbstractAnimation}. */
	@Override
	public abstract void interpolate(double frac);
	
	/** @throws IllegalStateException if {@link #isRunning()}. See {@link #restart()}. */
	@Override
	public void start() {
		if(isRunning())
			throw new IllegalStateException("Running");
		restart();
	}
	
	/** Restarts and unpauses this animation at the beginning, whether or not it is currently
	 * {@link #isRunning() running}.*/
	@Override
	public void restart() {
		elapsed = 0;
		paused = false;
	}
	
	@Override
	public void pause() {
		paused = true;
	}
	
	@Override
	public void unpause() {
		paused = false;
	}
	
	/** Returns a <em>new</em> {@link FinisherAnimation} that its equivalent to this {@link AbstractAnimation} but with the
	 * given {@link FinisherAnimation#finisher() finisher}. The given finisher is played after the finisher for this
	 * animation, if any. */
	@Override
	public FinisherAnimation withFinisher(Runnable finisher) {
		return new AnimationWithFinisher(this, finisher);
	}

	@Override
	public boolean isRunning() {
		return !isPaused() && elapsed >= 0 && !isFinished();
	}
	
	@Override
	public boolean isFinished() {
		return elapsed >= duration;
	}
	
	@Override
	public boolean isPaused() {
		return paused;
	}
	
	/** in nanos. */
	@Override
	public long duration() {
		return duration;
	}
	
}
