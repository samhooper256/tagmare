package visuals.gallery;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import visuals.Fonts;
import visuals.fxutils.Nodes;

final class OrderTip extends Label {

	public OrderTip() {
		super("Cards are shown from left to right and continue onto the next row.");
		Nodes.setLayout(this, Gallery.LEFT_X, Gallery.TIP_Y);
		setFont(Fonts.GEORGIA_18_ITALIC);
		setTextFill(Color.WHITE);
	}
	
}
