package visuals.combat;

import mechanics.modifiers.ModifierTag;
import visuals.fxutils.Nodes;

/** A {@link ModifierIcon} that shows the number below the icon image. */
public class VerticalModifierIcon extends AbstractModifierIcon {

	public static final double WIDTH = 32, HEIGHT = 52;
	
	public VerticalModifierIcon(ModifierTag tag) {
		super(tag);
		label().layoutYProperty().bind(label().heightProperty().negate().add(HEIGHT));
		label().layoutXProperty().bind(label().widthProperty().multiply(-.5).add(.5 * WIDTH));
		Nodes.setAllSizes(this, WIDTH, HEIGHT);
	}
	
}
