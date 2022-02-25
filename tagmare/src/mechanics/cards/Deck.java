package mechanics.cards;

import java.util.*;

import utils.*;

public class Deck implements Iterable<Card> {
	
	private static final List<CardTag> STARTING_DECK = Colls.ulist(
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK
	);
	
	public static Deck createStartingDeck() {
		Deck deck = new Deck();
		for(CardTag ct : STARTING_DECK)
			deck.add(ct.generate());
		return deck;
	}
	
	private final List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<>();
	}
	
	public void add(Card c) {
		cards.add(c);
	}
	
	/** Throws an {@link IllegalStateException} if the given {@link Card} is not in this {@link Deck}. */
	public void remove(Card c) {
		if(!cards.remove(c))
			throw new IllegalStateException(String.format("Card is not in Deck: %s", c));
	}
	
	@Override
	public Iterator<Card> iterator() {
		return Iterators.unmodifiable(cards.iterator());
	}
	
}
