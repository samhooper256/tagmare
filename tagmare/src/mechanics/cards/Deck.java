package mechanics.cards;

import java.util.*;

import utils.*;

import static mechanics.cards.CardTag.*;

public class Deck implements Iterable<Card> {
	
	private static final List<CardTag> STARTING_DECK = Colls.ulist(
		SPIRITUAL_ENLIGHTENMENT,
		SPIRITUAL_ENLIGHTENMENT,
		SUGAR_RUSH,
		CRAM,
		PLANNER,
		MEMORIZE,
		GRIND
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

	public Card get(int index) {
		return cards.get(index);
	}
	
	/** Unmodifiable. */
	public List<Card> cards() {
		return Collections.unmodifiableList(cards);
	}
	
	/** The returned {@link List} is modifiable; changes in the returned list do not affect this {@link Deck}.
	 * Every {@link Card} is a {@link Card#copy() copy} of the card from the {@link Deck}. */
	public List<Card> shuffledCopyOfCards() {
		List<Card> copy = new ArrayList<>(cards);
		Collections.shuffle(copy, RNG.SOURCE);
		copy.replaceAll(Card::copy);
		return copy;
	}
	
	@Override
	public Iterator<Card> iterator() {
		return Iterators.unmodifiable(cards.iterator());
	}
	
	@Override
	public String toString() {
		return String.format("Deck%s", cards);
	}
	
}
