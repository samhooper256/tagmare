package visuals.combat.inquiry;

import java.util.*;

import mechanics.cards.Card;
import visuals.*;

public class PhantomCard extends AbstractCardRepresentation {

	private static final Map<Card, PhantomCard> MAP = new WeakHashMap<>();
	
	public static PhantomCard of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new PhantomCard(card));
		PhantomCard pc = MAP.get(card);
		pc.updateText();
		return pc;
	}
	
	private PhantomCard(Card card) {
		super(card);
		card.updateText();
		setOnMouseClicked(eh -> mouseClicked());
	}
	
	private void mouseClicked() {
		Vis.inquiryLayer().clickedPhantom(card);
	}
	
}
