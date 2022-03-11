package visuals;

import java.util.*;

import base.Updatable;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import mechanics.cards.*;
import visuals.fxutils.*;

/** Not {@link Updatable}. */
public abstract class AbstractCardRepresentation extends StackPane {

	public static final double 
		WIDTH = 176, HEIGHT = WIDTH * 1.5;
	
	public static final Font NAME_FONT = Fonts.UI_18_BOLD, TEXT_FONT = Fonts.UI_14;
	
	private final VBox vBox;
	private final Label name;
	private final Text text;
	private final Sprite base;
	protected final List<Node> faceUpChildren;
	protected final Card card;
	
	public AbstractCardRepresentation(Card card) {
		this.card = card;
		name = Nodes.label(NAME_FONT);
		name.setWrapText(true);
		updateName();
		text = Nodes.text(card.text().defaultText(), TEXT_FONT);
		text.setWrappingWidth(WIDTH);
		text.setTextAlignment(TextAlignment.CENTER);
		base = new Sprite(baseImage(card));
		vBox = new VBox(name, text);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPadding(new Insets(4, 0, 0, 0));
		faceUpChildren = new ArrayList<>();
		Collections.addAll(faceUpChildren, base, new Sprite(Images.CARD_BORDER), vBox);
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
		text.setText(card.text().displayText());
	}
	
	private void updateName() {
		name.setText(String.format("%s (%d)\n", card.displayName(), card.energyCost()));
	}
	
	protected void setChildren(List<Node> children) {
		getChildren().clear();
		getChildren().addAll(children);
	}
	
}
