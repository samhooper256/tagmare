package visuals.animations;

import javafx.scene.Node;

public class TestFade extends AbstractAnimation {

	private static final long DURATION = (long) 1e9;
	
	private final Node node;
	
	public TestFade(Node node) {
		super(DURATION);
		this.node = node;
	}

	@Override
	public void interpolate(double frac) {
		node.setOpacity(.8 * frac + .2);
	}
	
	public Node node() {
		return node;
	}
	
}
