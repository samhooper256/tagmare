package visuals.tooltips;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import visuals.*;
import visuals.fxutils.*;

public class Tooltip extends VBox {

	public static final double WIDTH = 100;
	public static final Font TITLE_FONT = Fonts.GEORGIA_12_BOLD, DESCRIPTION_FONT = Fonts.GEORGIA_12;
	
	private final Label title, description;
	
	/** {@link #title()} and {@link #description()} will be empty strings. */
	public Tooltip() {
		this("", "");
	}
	
	/** {@link #description()} will be an empty string. */
	public Tooltip(String title) {
		this(title, "");
	}
	
	public Tooltip(String title, String description) {
		this.title = Nodes.label(title, TITLE_FONT, Colors.TAG_GOLD);
		this.title.setWrapText(true);
		this.description = Nodes.label(description, DESCRIPTION_FONT, Color.WHITE);
		this.description.setWrapText(true);
		setBackground(Backgrounds.of(Colors.TAG_BLUE));
		Nodes.setAllWidths(this, WIDTH);
		getChildren().addAll(this.title, this.description);
	}
	
	public void setTitle(String title) {
		this.title.setText(title);
	}
	
	public String title() {
		return title.getText();
	}
	
	public void setDescription(String description) {
		this.description.setText(description);
	}
	
	public String description() {
		return description.getText();
	}
	
	/** Called immediately before this {@link Tooltip} is displayed (every time) if it is in a {@link TooltipColumn}.
	 * Does nothing by default. */
	public void update() {
		//nothing by default
	}
	
}
