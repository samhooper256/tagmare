package visuals;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mechanics.modifiers.ModifierTag;
import visuals.fxutils.*;

public class AbstractModifierIcon extends Pane {

	public static final double WIDTH = 32, HEIGHT = 32;
	public static final String CSS = "modifier-icon";
	
	private static final double LABEL_Y = 10;
	private static final Font FONT = Fonts.UI_24_BOLD;
	private static final Color TEXT_COLOR = Color.WHITE;
	
	private final ModifierTag tag;
	private final Sprite sprite;
	private final Label label;
	
	public AbstractModifierIcon(ModifierTag tag) {
		this.tag = tag;
		sprite = new Sprite(Images.forModifier(tag));
		label = Nodes.label(FONT, TEXT_COLOR);
		label.setLayoutY(LABEL_Y);
		label.layoutXProperty().bind(label.widthProperty().negate().add(WIDTH + 2));
		Nodes.setAllSizes(this, WIDTH, HEIGHT);
		getChildren().addAll(sprite, label);
		getStyleClass().add(CSS);
	}
	
	public ModifierTag tag() {
		return tag;
	}
	
	public void setInteger(int integer) {
		if(!tag.isIntegerModifier())
			throw new UnsupportedOperationException(String.format("Not an integer Modifier: %s", tag));
		label.setText(String.valueOf(integer));
	}
	
}
