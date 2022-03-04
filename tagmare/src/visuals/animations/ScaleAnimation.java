package visuals.animations;

import javafx.scene.layout.Region;
import javafx.util.Duration;
import visuals.fxutils.Nodes;

public class ScaleAnimation extends AbstractAnimation {

	private final Region region;
	private final double factor;
	
	public ScaleAnimation(Duration duration, Region region, double factor) {
		super(duration);
		this.region = region;
		this.factor = factor;
	}
	
	public Region region() {
		return region;
	}
	
	public double factor() {
		return factor;
	}

	@Override
	public void interpolate(double frac) {
		double f = 1 + (factor - 1) * frac;
		Nodes.setScale(region, f);
	}
	
}
