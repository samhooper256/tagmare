package visuals.animations;

import javafx.scene.layout.Region;
import javafx.util.Duration;
import visuals.fxutils.Nodes;

public class ScaleAnimation extends AbstractAnimation {

	private final Region region;
	private final double destScale, startScale;
	
	/** Uses the {@link Region Region's} current {@link Region#scaleXProperty() scale x} as the
	 * {@link #startScale()}. */
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

	public double startScale() {
		return startScale;
	}
	
	public double destScale() {
		return destScale;
	}

	@Override
	public void interpolate(double frac) {
		double f = startScale + (destScale - startScale) * frac;
		Nodes.setScale(region, f);
	}
	
}
