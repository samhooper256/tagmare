package visuals.gallery;

import java.util.WeakHashMap;

import mechanics.cards.Card;
import visuals.AbstractCardRepresentation;
import visuals.tooltips.CardTooltipManager;

public class GalleryCard extends AbstractCardRepresentation {

	private static final WeakHashMap<Card, GalleryCard> MAP = new WeakHashMap<>();
	
	public static GalleryCard of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new GalleryCard(card));
		return MAP.get(card);
	}
	
	private GalleryCard(Card card) {
		super(card);
		CardTooltipManager.install(this);
	}
	
}
