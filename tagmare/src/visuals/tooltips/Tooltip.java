package visuals.tooltips;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import visuals.*;
import visuals.fxutils.*;

public class Tooltip extends VBox {

	public static final double WIDTH = 120, PADDING = 2, BACKGROUND_ROUNDING = 3;
	public static final Font TITLE_FONT = Fonts.GEORGIA_14_BOLD, DESCRIPTION_FONT = Fonts.GEORGIA_14;
	
	private Label title, description;
	
	/** Returns a new untitled {@link Tooltip} with an empty description. */
	public static Tooltip untitled() {
		return untitled("");
	}

	/** Returns a new untitled {@link Tooltip} with the given {@code description}. */
	public static Tooltip untitled(String description) {
		Tooltip t = new Tooltip();
		addDescription(t, description);
		return t;
	}
	
	/** Returns a new {@link Tooltip} with the given {@code title} and an empty description. */
	public static Tooltip titled(String title) {
		return titled(title, "");
	}
	
	/** Returns a new {@link Tooltip} with the given {@code title} and {@code description}. */
	public static Tooltip titled(String title, String description) {
		Tooltip t = new Tooltip();
		addTitle(t, title);
		addDescription(t, description);
		return t;
	}
	
	private static void addTitle(Tooltip tooltip, String title) {
		tooltip.title = Nodes.label(title, TITLE_FONT, Colors.TAG_GOLD);
		tooltip.title.setWrapText(true);
		tooltip.getChildren().add(tooltip.title);
	}
	
	private static void addDescription(Tooltip tooltip, String description) {
		tooltip.description = Nodes.label(description, DESCRIPTION_FONT, Color.WHITE);
		tooltip.description.setWrapText(true);
		tooltip.getChildren().add(tooltip.description);
	}
	
	private Tooltip() {
		setPadding(new Insets(PADDING));
		setBackground(Backgrounds.rounded(Colors.TAG_BLUE, BACKGROUND_ROUNDING));
		Nodes.setAllWidths(this, WIDTH);
	}
	
	/** If this {@link Tooltip} is not {@link #isTitled() titled}, this method has no effect. */
	public void setTitle(String title) {
		if(isTitled())
			this.title.setText(title);
	}
	
	/** If this {@link Tooltip} is not {@link #isTitled() titled}, returns an empty string. */
	public String title() {
		return isTitled() ? title.getText() : "";
	}
	
	public void setDescription(String description) {
		this.description.setText(description);
	}
	
	public String description() {
		return description.getText();
	}
	
	public boolean isTitled() {
		return title != null;
	}
	
	/** Called immediately before this {@link Tooltip} is displayed (every time) if it is in a {@link TooltipColumn}.
	 * Does nothing by default. */
	public void update() {
		//nothing by default
	}
	
}
