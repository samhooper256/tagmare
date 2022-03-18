package visuals.combat;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mechanics.modifiers.ModifierTag;
import visuals.*;
import visuals.fxutils.*;

/** The {@link #sprite()} and {@link #label()} are the only children by default; they both have their layout coordinates
 * at (0, 0). */
public abstract class AbstractModifierIcon extends Pane implements ModifierIcon {

	public static final String CSS = "modifier-icon";
	
	private static final Font FONT = Fonts.NUMBERS_24_BOLD;
	private static final Color TEXT_COLOR = Color.WHITE;
	
	private final ModifierTag tag;
	private final Sprite sprite;
	private final Label label;
	
	public AbstractModifierIcon(ModifierTag tag) {
		this.tag = tag;
		sprite = new Sprite(Images.forModifier(tag));
		label = Nodes.label(FONT, TEXT_COLOR);
		getChildren().addAll(sprite, label);
		getStyleClass().add(CSS);
	}

	protected Sprite sprite() {
		return sprite;
	}
	
	protected Label label() {
		return label;
	}
	
	@Override
	public ModifierTag tag() {
		return tag;
	}
	
	@Override
	public void setInteger(int integer) {
		if(!tag().isIntegerModifier())
			throw new UnsupportedOperationException(String.format("Not an integer Modifier: %s", tag()));
		label.setText(String.valueOf(integer));
	}
	
}
