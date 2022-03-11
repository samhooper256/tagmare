package visuals;

import java.util.*;

import base.Updatable;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import mechanics.cards.*;
import visuals.fxutils.*;

/** Not {@link Updatable}. */
public abstract class AbstractCardRepresentation extends StackPane {

	public static final double  WIDTH = 176, HEIGHT = WIDTH * 1.5;
	
	private static final double
		NAME_WRAP_TOP_PADDING = 22,
		TEXT_WRAP_TOP_PADDING = 150,
		TEXT_WRAP_WIDTH = WIDTH - 16;
	
	public static final double IMAGE_WIDTH = 144, IMAGE_Y = 52;
	
	public static final Font NAME_FONT = Fonts.UI_18_BOLD, TEXT_FONT = Fonts.UI_14;
	
	private static final Color ENERGY_COLOR = Color.rgb(255, 218, 184);
	
	private final VBox nameWrap, textWrap, energyWrap;
	private final Label name, energy;
	private final Text text;
	private final Pane imagePane;
	private final Sprite base, border, energyTab, imageBorder, image;
	protected final List<Node> faceUpChildren;
	protected final Card card;
	
	public AbstractCardRepresentation(Card card) {
		this.card = card;
		name = Nodes.label(NAME_FONT);
		name.setWrapText(true);
		updateName();
		text = Nodes.text(card.text().displayText(), TEXT_FONT);
		text.setWrappingWidth(TEXT_WRAP_WIDTH);
		text.setTextAlignment(TextAlignment.CENTER);
		energy = Nodes.label(NAME_FONT, ENERGY_COLOR);
		energy.setText("1");
		energyWrap = new VBox(energy);
		energyWrap.setAlignment(Pos.TOP_CENTER);
		base = new Sprite(baseImage(card));
		energyTab = new Sprite(Images.CARD_ENERGY_TAB);
		border = new Sprite(Images.CARD_BORDER);
		nameWrap = new VBox(name);
		nameWrap.setAlignment(Pos.TOP_CENTER);
		nameWrap.setPadding(new Insets(NAME_WRAP_TOP_PADDING, 0, 0, 0));
		textWrap = new VBox(text);
		textWrap.setAlignment(Pos.TOP_CENTER);
		textWrap.setPadding(new Insets(TEXT_WRAP_TOP_PADDING, 0, 0, 0));
		image = new Sprite(Images.forCard(card));
		Nodes.setLayout(image, .5 * (WIDTH - IMAGE_WIDTH), IMAGE_Y);
		imagePane = new Pane(image);
		imageBorder = new Sprite(Images.CARD_IMAGE_BORDER);
		faceUpChildren = new ArrayList<>();
		Collections.addAll(faceUpChildren, base, border, energyTab, energyWrap, nameWrap, textWrap, imageBorder, imagePane);
		getChildren().addAll(faceUpChildren);
	}
	
	private Image baseImage(Card card) {
		if(card instanceof Attack)
			return Images.ATTACK_BASE;
		if(card instanceof Skill)
			return Images.SKILL_BASE;
		if(card instanceof Passive)
			return Images.PASSIVE_BASE;
		throw new UnsupportedOperationException(String.format("Attack type: %s", card.getClass().getSimpleName()));
	}
	
	public Card card() {
		return card;
	}
	
	public void updateText() {
		card().updateText();
		updateName();
//		text.setText(card.text().displayText());
	}
	
	private void updateName() {
		name.setText(card.displayName());
	}
	
	protected void setChildren(List<Node> children) {
		getChildren().clear();
		getChildren().addAll(children);
	}
	
}
