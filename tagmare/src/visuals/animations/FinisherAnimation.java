package visuals.animations;

public interface FinisherAnimation extends Animation {

	Runnable finisher();
	
	default AbstractAnimation asAnimation() {
		return (AbstractAnimation) this;
	}
	
	@Override
	default void interpolate(double frac) {
		interpolateWithoutFinisher(frac);
		if(frac == 1)
			finisher().run();
	}
	
	void interpolateWithoutFinisher(double frac);
	
}
