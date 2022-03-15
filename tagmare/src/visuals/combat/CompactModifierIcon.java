package visuals.combat;

import mechanics.modifiers.ModifierTag;
import visuals.fxutils.Nodes;

public class CompactModifierIcon extends AbstractModifierIcon implements ModifierIcon {

	public static final double WIDTH = 32, HEIGHT = 32;
	
	private static final double LABEL_Y = 10;
	
	public CompactModifierIcon(ModifierTag tag) {
		super(tag);
		label().setLayoutY(LABEL_Y);
		label().layoutXProperty().bind(label().widthProperty().negate().add(WIDTH + 2));
		Nodes.setAllSizes(this, WIDTH, HEIGHT);
	}
	
}
