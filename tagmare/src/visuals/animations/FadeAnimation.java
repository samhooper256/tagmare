package visuals.animations;

import javafx.scene.Node;
import javafx.util.Duration;

/** {@link #from()} and {@link #to()} are {@code 0.0} and {@code 1.0} by default. */
public class FadeAnimation extends AbstractAnimation {

	private final Node node;
	
	private double from, to;
	
	public FadeAnimation(Node node, Duration duration) {
		this(node, duration, 0, 1);
	}

	public FadeAnimation(Node node, Duration duration, double from, double to) {
		super(duration);
		this.node = node;
		this.from = from;
		this.to = to;
	}
	
	@Override
	public void interpolate(double frac) {
		node.setOpacity(from + (to - from) * frac);
	}
	
	public FadeAnimation setFrom(double from) {
		this.from = from;
		return this;
	}
	
	public FadeAnimation setTo(double to) {
		this.to = to;
		return this;
	}
	
	public double from() {
		return from;
	}

	public double to() {
		return to;
	}
	
}
