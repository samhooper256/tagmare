package base.animations;

/** All implementing classes must extend {@link Animation}. */
public interface FinisherAnimation {

	Runnable finisher();
	
	default Animation asAnimation() {
		return (Animation) this;
	}
	
	default void interpolate(double frac) {
		interpolateWithoutFinisher(frac);
		if(frac == 1)
			finisher().run();
	}
	
	void interpolateWithoutFinisher(double frac);
	
}
