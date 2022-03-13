package visuals.combat.piles;

import java.util.List;

import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import mechanics.cards.Card;
import visuals.*;
import visuals.fxutils.*;

abstract class PileSublayer extends Pane {

	private final Group cardGroup;
	private final Sprite backlight;
	private final double cardX, cardY;
	
	protected PileSublayer(double x, double y) {
		this.cardX = x;
		this.cardY = y;
		cardGroup = new Group();
		backlight = new Sprite(Images.CARD_BACKLIGHT);
		backlight.setLayoutX(cardX - (Images.CARD_BACKLIGHT.getWidth() - AbstractCardRepresentation.WIDTH) * .5);
		backlight.setLayoutY(cardY - (Images.CARD_BACKLIGHT.getHeight() - AbstractCardRepresentation.HEIGHT) * .5);
		backlight.setVisible(false);
		getChildren().addAll(backlight, cardGroup);
		setPickOnBounds(false);
		setOnMouseEntered(me -> hoverEntered());
		setOnMouseExited(me -> hoverExited());
		setOnMouseClicked(this::clicked);
	}
	
	public void setCards(Iterable<Card> cardsBottomToTop) {
		cardRepresentations().clear();
		for(Card card : cardsBottomToTop)
			addCardToTop(card);
	}
	
	public void addCardToTop(Card card) {
		CardRepresentation cr = CardRepresentation.of(card);
		addToTop(cr);
	}
	
	public void addToTop(CardRepresentation cr) {
		cr.setFaceDown();
		Nodes.setLayout(cr, cardX, cardY);
		cardGroup().getChildren().add(cr);
	}
	
	public void clear() {
		cardRepresentations().clear();
	}
	
	protected Group cardGroup() {
		return cardGroup;
	}
	
	public List<Node> cardRepresentations() {
		return cardGroup.getChildren();
	}
	
	private void hoverEntered() {
		backlight.setVisible(true);
	}
	
	private void hoverExited() {
		backlight.setVisible(false);
	}
	
	protected abstract void clicked(MouseEvent me);
	
}
