package visuals.gallery;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import utils.English;
import visuals.Fonts;
import visuals.fxutils.Nodes;

/** To change the description, use {@link #setDescription(String)} (not {@link #setText(String)}).*/
public class GalleryDescription extends Label {

	private String description;
	private int cardCount;
	
	public GalleryDescription(String description) {
		super();
		this.description = description;
		this.cardCount = 0;
		setTextFor(cardCount);
		Nodes.setLayout(this, Gallery.LEFT_X, Gallery.DESCRIPTION_Y);
		setFont(Fonts.GEORGIA_36);
		setTextFill(Color.WHITE);
	}

	public void setTextFor(int cardCount) {
		setText(String.format("%s (%s)", description, English.pluralSpaced("card", cardCount)));
		this.cardCount = cardCount;
	}
	
	public void setDescription(String description) {
		this.description = description;
		setTextFor(cardCount);
	}

	public String description() {
		return description;
	}
	
	/** Can only be changed by {@link #setTextFor(int)}. */
	public int cardCount() {
		return cardCount;
	}
	
}
