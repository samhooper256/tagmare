package visuals;

import java.util.WeakHashMap;

import base.temp.Backgrounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import mechanics.cards.Card;
import visuals.fxutils.Nodes;

public final class CardRepresentation extends StackPane {

	public static final double WIDTH = 176, HEIGHT = WIDTH * 1.5;
	
	private static final WeakHashMap<Card, CardRepresentation> MAP = new WeakHashMap<>();
	
	public static CardRepresentation of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new CardRepresentation(card));
		return MAP.get(card);
	}
	
	private final Card card;
	
	private CardRepresentation(Card card) {
		this.card = card;
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		setBackground(Backgrounds.of(Color.BISQUE));
	}
	
	public Card card() {
		return card;
	}
	
}
