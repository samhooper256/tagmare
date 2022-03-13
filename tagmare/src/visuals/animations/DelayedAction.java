package visuals.animations;

import javafx.util.Duration;

/** An {@link Animation} that does nothing other than execute its {@link #finishAction()} when it's done. */
public class DelayedAction extends AbstractAnimation {

	public DelayedAction(Duration duration, Runnable finisher) {
		super(duration);
		setFinish(finisher);
	}

	@Override
	public void interpolate(double frac) {
		//nothing
	}
	
}
