package visuals.piles;

import java.util.List;

import javafx.scene.layout.Pane;
import mechanics.cards.Card;
import visuals.CardRepresentation;
import visuals.fxutils.Nodes;

public class DrawPileLayer extends Pane {

	public static final double CARD_X = 100, CARD_Y = 100;
	
	public DrawPileLayer() {
		
	}
	
	public void setCards(List<Card> cardsBottomToTop) {
		getChildren().clear();
		for(Card card : cardsBottomToTop) {
			CardRepresentation cr = CardRepresentation.of(card);
			cr.setFaceDown();
			Nodes.setLayout(cr, CARD_X, CARD_Y);
			getChildren().add(cr);
		}
	}
	
}
