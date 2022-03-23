package visuals.combat;

import javafx.geometry.Side;
import mechanics.modifiers.ModifierTag;
import visuals.fxutils.*;

/** A {@link ModifierIcon} that shows the number below the icon image. */
public class VerticalModifierIcon extends TippedModifierIcon {

	public static final double WIDTH = 32, HEIGHT = 52;
	
	public VerticalModifierIcon(ModifierTag tag) {
		super(tag, Side.TOP);
		if(tag.isIntegerModifier()) {
			label().layoutYProperty().bind(label().heightProperty().negate().add(HEIGHT));
			label().layoutXProperty().bind(label().widthProperty().multiply(-.5).add(.5 * WIDTH));
		}
		else {
			getChildren().remove(label());
			sprite().setLayoutY(.5 * (HEIGHT - Images.MODIFIER_ICON_HEIGHT));
		}
		Nodes.setAllSizes(this, WIDTH, HEIGHT);
	}
	
}
