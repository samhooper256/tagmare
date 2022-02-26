package base.animations;

import javafx.scene.Node;

public class HalfFade extends AbstractAnimation {

	private static final long DURATION = (long) 1e9;
	
	private final Node node;
	
	public HalfFade(Node node) {
		super(DURATION);
		this.node = node;
	}

	@Override
	public void interpolate(double frac) {
		node.setOpacity(2 * Math.abs(frac - 0.5));
	}
	
	public Node node() {
		return node;
	}
	
}
