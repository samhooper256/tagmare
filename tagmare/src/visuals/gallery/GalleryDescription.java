package visuals.gallery;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import visuals.Fonts;
import visuals.fxutils.Nodes;

public class GalleryDescription extends Label {

	public GalleryDescription(String text) {
		super(text);
		Nodes.setLayout(this, Gallery.LEFT_X, Gallery.DESCRIPTION_Y);
		setFont(Fonts.GEORGIA_36);
		setTextFill(Color.WHITE);
	}
	
}
