package visuals.animations;

import javafx.util.Duration;

/** Animations are not {@link #isPaused() paused} by default. Any {@link #update(long)} calls will cause the
 * {@link AbstractAnimation} to progress. The {@link #interpolator()} is linear by default. */
public abstract class AbstractAnimation implements Animation {

	private static long asNanos(Duration duration) {
		if(duration.isIndefinite() || duration.isUnknown())
			throw new IllegalArgumentException(String.format("Invalid duration: %s", duration));
		return (long) (duration.toMillis() * 1e6);
	}
	
	private final long duration;
	
	private Interpolator interpolator;
	private double rate;
	private long elapsed;
	private boolean paused;
	private Runnable onFinish, reverseFinish;
	
	public AbstractAnimation(Duration duration) {
		this(duration, Interpolator.LINEAR);
	}
	
	public AbstractAnimation(Duration duration, Interpolator interpolator) {
		this(asNanos(duration), interpolator);
	}
	
	/** @param duration in nanos. */
	public AbstractAnimation(long duration) {
		this(duration, Interpolator.LINEAR);
	}
	
	/** @param duration in nanos. */
	public AbstractAnimation(long duration, Interpolator interpolator) {
		this.duration = duration;
		this.interpolator = interpolator;
		elapsed = 0;
		rate = 1;
		paused = false;
	}
	
	/** Does nothing if {@link #isPaused()} or {@link #isFinished()}. */
	@Override
	public final void update(long diff) {
		if(isPaused() || isFinished())
			return;
		elapsed += diff * rate;
		if(elapsed >= duration) {
			interpolate(1);
			if(rate > 0)
				runFinishAction();
			elapsed = duration;
		}
		else if(elapsed <= 0) {
			interpolate(0);
			if(rate < 0)
				runReverseFinishAction();
			elapsed = -1;
		}
		else {
			interpolate(progressAfterInterpolator());
		}
	}
	
	private double progressBeforeInterpolator() {
		return 1d * elapsed / duration;
	}
	
	private double progressAfterInterpolator() {
		return interpolator().applyAsDouble(progressBeforeInterpolator());
	}
	
	@Override
	public abstract void interpolate(double frac);
	
	@Override
	public void start() {
		if(isRunning())
			throw new IllegalStateException("Running");
		restart();
	}
	
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
	
	@Override
	public AbstractAnimation setFinish(Runnable onFinish) {
		this.onFinish = onFinish;
		return this;
	}
	
	@Override
	public Runnable finishAction() {
		return onFinish;
	}
	
	@Override
	public AbstractAnimation setReverseFinish(Runnable reverseFinish) {
		this.reverseFinish = reverseFinish;
		return this;
	}
	
	/** An action that will be played when the animation is playing with a negative {@link #rate()} and reaches the
	 * start of the animation. */
	@Override
	public Runnable reverseFinishAction() {
		return reverseFinish;
	}
	
	private void runFinishAction() {
		if(onFinish != null)
			onFinish.run();
	}
	
	private void runReverseFinishAction() {
		if(reverseFinish != null)
			reverseFinish.run();
	}

	@Override
	public boolean isRunning() {
		return !isPaused() && elapsed >= 0 && !isFinished();
	}
	
	@Override
	public boolean isFinished() {
		return elapsed < 0 || elapsed >= duration;
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
	
	@Override
	public Interpolator interpolator() {
		return interpolator;
	}

	@Override
	public AbstractAnimation setInterpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
		return this;
	}
	
	@Override
	public double rate() {
		return rate;
	}
	
	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
