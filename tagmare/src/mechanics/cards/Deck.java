package mechanics.cards;

import java.util.*;

import utils.*;

public class Deck implements Iterable<Card> {
	
	private static final List<CardTag> STARTING_DECK = Colls.ulist(
		CardTag.REVIEW_NOTES,
//		CardTag.REVIEW_NOTES,
//		CardTag.REVIEW_NOTES,
//		CardTag.REVIEW_NOTES,
//		CardTag.REVIEW_NOTES,
		CardTag.ALL_NIGHTER,
//		CardTag.PROCRASTINATE,
//		CardTag.PROCRASTINATE,
		CardTag.PROCRASTINATE,
		CardTag.DO_HOMEWORK,
		CardTag.DO_HOMEWORK
//		CardTag.DO_HOMEWORK,
//		CardTag.QUIZLET,
//		CardTag.QUIZLET,
//		CardTag.QUIZLET,
//		CardTag.RAGE,
//		CardTag.RAGE,
//		CardTag.RAGE,
//		CardTag.DO_HOMEWORK,
//		CardTag.GRIND
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
	
	/** The returned copy is modifiable. */
	public List<Card> shuffledCopyOfCards() {
		List<Card> copy = new ArrayList<>(cards);
		Collections.shuffle(copy, RNG.SOURCE);
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
