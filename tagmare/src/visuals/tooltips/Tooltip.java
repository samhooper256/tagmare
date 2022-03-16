package visuals.tooltips;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import visuals.*;
import visuals.fxutils.Nodes;

public class Tooltip extends VBox {

	public static final double WIDTH = 100;
	public static final Font TITLE_FONT = Fonts.GEORGIA_12_BOLD, DESCRIPTION_FONT = Fonts.GEORGIA_12;
	
	private final Label title, description;
	
	public Tooltip(String title, String description) {
		this.title = Nodes.label(title, TITLE_FONT, Colors.GOLD);
		this.title.setWrapText(true);
		this.description = Nodes.label(description, DESCRIPTION_FONT, Color.WHITE);
		this.description.setWrapText(true);
		Nodes.setAllWidths(this, WIDTH);
		getChildren().addAll(this.title, this.description);
	}
	
	public void setTitle(String title) {
		this.title.setText(title);
	}
	
	public void setDescription(String description) {
		this.description.setText(description);
	}
	
}
