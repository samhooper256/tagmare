package base.temp;

import java.util.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.cards.Card;

public class HandCardRep extends HBox {
	
	private static final Background BACKGROUND = Backgrounds.of(Color.PINK);
	
	private static final Map<Card, HandCardRep> MAP = new HashMap<>();
	
	public static HandCardRep of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new HandCardRep(card));
		return MAP.get(card);
	}
	
	private final Card card;
	private final Text text;
	
	private boolean selected;
	
	private HandCardRep(Card card) {
		text = new Text(card.toString());
		this.card = card;
		this.selected = true;
		getChildren().add(text);
		setOnMouseClicked(eh -> mouseClicked());
	}
	
	private void mouseClicked() {
		GameScene.INSTANCE.handDisplay.clicked(this);
	}
	
	public boolean selected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		setBackground(selected ? BACKGROUND : null);
	}
	
	public Card card() {
		return card;
	}
	
}