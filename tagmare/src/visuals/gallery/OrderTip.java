package visuals.gallery;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import visuals.Fonts;
import visuals.fxutils.Nodes;

/** Any additional text should be punctuated and should not start with leading whitespace.
 * Pass an empty string for no additional text. */
final class OrderTip extends Label {

	private static String getText(String additionalTipText) {
		return "Cards are shown from left to right and continue onto the next row." +
			(additionalTipText.isEmpty() ? "" : " " + additionalTipText);
	}
	
	public OrderTip(String additionalTipText) {
		super(getText(additionalTipText));
		Nodes.setLayout(this, Gallery.LEFT_X, Gallery.TIP_Y);
		setFont(Fonts.GEORGIA_18_ITALIC);
		setTextFill(Color.WHITE);
	}

	public void setAdditionalTipText(String additionalTipText) {
		setText(getText(additionalTipText));
	}
	
}
