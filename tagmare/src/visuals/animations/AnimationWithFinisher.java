package visuals.animations;

import java.util.Objects;

/** If {@link #base()} is already a {@link FinisherAnimation}, its {@link FinisherAnimation#finisher() finisher} is
 * played first, then the {@link #finisher()} given to {@code this} {@link AnimationWithFinisher} is played. */
public class AnimationWithFinisher extends AbstractAnimation implements FinisherAnimation {

	private final AbstractAnimation base;
	private final Runnable finisher;
	
	public AnimationWithFinisher(AbstractAnimation base, Runnable finisher) {
		super(base.duration());
		Objects.requireNonNull(finisher);
		this.base = base;
		this.finisher = finisher;
	}
	
	@Override
	public void interpolateWithoutFinisher(double frac) {
		base.interpolate(frac); //if frac is 1, this will case base's finisher (if any).
	}
	
	@Override
	public void interpolate(double frac) {
		//if frac is 1, this will run the finisher AFTER calling interpolateWithoutFinisher.
		FinisherAnimation.super.interpolate(frac);
	}
	
	public AbstractAnimation base() {
		return base;
	}
	
	@Override
	public Runnable finisher() {
		return finisher;
	}
	
}
