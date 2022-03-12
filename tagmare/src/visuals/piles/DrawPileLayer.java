package visuals.piles;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import mechanics.cards.Card;
import visuals.CardRepresentation;
import visuals.fxutils.Nodes;

public class DrawPileLayer extends Pane {

	public static final double CARD_X = 100, CARD_Y = 100;
	
	public DrawPileLayer() {
		setMouseTransparent(true);
	}
	
	public void setCards(Iterable<Card> cardsBottomToTop) {
		System.out.printf("setCards(%s)%n", cardsBottomToTop);
		getChildren().clear();
		for(Card card : cardsBottomToTop)
			addCardToTop(card);
	}

	public void addCardToTop(Card card) {
		CardRepresentation cr = CardRepresentation.of(card);
		cr.setFaceDown();
		Nodes.setLayout(cr, CARD_X, CARD_Y);
		getChildren().add(cr);
	}
	
	public List<Node> cardRepresentations() {
		return getChildren();
	}
	
}
