package mechanics.combat;

import java.util.*;

import mechanics.cards.*;
import utils.RNG;

public final class CombatReward {

	public static final int CARD_COUNT = 3;
	
	private final List<Card> cards;
	
	public CombatReward() {
		List<Card> cardsModifiable = new ArrayList<>(CARD_COUNT);
		for(CardTag ct : RNG.pickUnique(CardTag.rewardable(), CARD_COUNT))
			cardsModifiable.add(ct.generate());
		cards = Collections.unmodifiableList(cardsModifiable);
	}
	
	/** 0-based index. */
	public Card get(int index) {
		return cards().get(index);
	}
	
	/** Unmodifiable. */
	public List<Card> cards() {
		return cards;
	}
	
}
