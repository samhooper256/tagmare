package visuals;

import java.util.*;

import base.Updatable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import mechanics.cards.Card;
import visuals.fxutils.*;

/** Not {@link Updatable}. */
public abstract class AbstractCardRepresentation extends StackPane {

	public static final double 
		WIDTH = 176, HEIGHT = WIDTH * 1.5;
	
	public static final Font NAME_FONT = Fonts.UI_18_BOLD, TEXT_FONT = Fonts.UI_14;
	
	private final VBox vBox;	
	private final Text name, text;
	protected final List<Node> faceUpChildren;
	protected final Card card;
	
	public AbstractCardRepresentation(Card card) {
		this.card = card;
		name = new Text(String.format("%s (%d)\n", card.displayName(), card.energyCost()));
		name.setWrappingWidth(WIDTH);
		name.setFont(NAME_FONT);
		text = Nodes.text(card.text().defaultText(), TEXT_FONT);
		text.setWrappingWidth(WIDTH);
		vBox = new VBox(name, text);
		vBox.setAlignment(Pos.TOP_CENTER);
		faceUpChildren = new ArrayList<>();
		Collections.addAll(faceUpChildren, new Sprite(Images.CARD_BASE), vBox);
		getChildren().addAll(faceUpChildren);
	}
	
	public Card card() {
		return card;
	}
	
	public void updateText() {
		card().updateText();
		text.setText(card.text().displayText());
	}
	
	protected void setChildren(List<Node> children) {
		getChildren().clear();
		getChildren().addAll(children);
	}
	
}
