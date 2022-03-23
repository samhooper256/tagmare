package visuals.combat;

import javafx.geometry.Side;
import mechanics.modifiers.*;
import visuals.fxutils.Nodes;

public class CompactModifierIcon extends TippedModifierIcon implements ModifierIcon {

	public static final double WIDTH = 32, HEIGHT = 32;
	
	private static final double LABEL_Y = 10;
	private static final Side SIDE = Side.BOTTOM;
	
	public CompactModifierIcon(ModifierTag tag) {
		super(tag, SIDE);
		layoutAndSize();
	}

	/** Does <em>NOT</em> store a reference to the given {@link Modifier}. */
	public CompactModifierIcon(Modifier m) {
		super(m.tag(), SIDE);
		layoutAndSize();
		if(m.isInteger())
			setInteger(m.integer());
	}
	
	private void layoutAndSize() {
		label().setLayoutY(LABEL_Y);
		label().layoutXProperty().bind(label().widthProperty().negate().add(WIDTH + 2));
		Nodes.setAllSizes(this, WIDTH, HEIGHT);
	}
	
}
