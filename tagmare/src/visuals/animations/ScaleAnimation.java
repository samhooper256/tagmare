package visuals.animations;

import javafx.scene.layout.Region;
import javafx.util.Duration;
import visuals.fxutils.Nodes;

public class ScaleAnimation extends AbstractAnimation {

	private final Region region;
	private final double destScale, startScale;
	
	public ScaleAnimation(Duration duration, Region region, double destScale) {
		this(duration, region, destScale, region.getScaleX());
	}
	
	public ScaleAnimation(Duration duration, Region region, double destScale, double startScale) {
		super(duration);
		this.region = region;
		this.startScale = startScale;
		this.destScale = destScale;
	}
	
	public Region region() {
		return region;
	}
	
	public double factor() {
		return destScale;
	}

	@Override
	public void interpolate(double frac) {
		double f = startScale + (destScale - startScale) * frac;
		Nodes.setScale(region, f);
	}
	
}
