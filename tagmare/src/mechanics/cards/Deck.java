package mechanics.cards;

import java.util.*;

import utils.*;

import static mechanics.cards.CardTag.*;

public class Deck implements Iterable<Card> {
	
	private static final List<CardTag> STARTING_DECK = Colls.ulist(
		BRAG,
		PROCRASTINATE,
		DO_HOMEWORK
	);
	/*
	private static final List<CardTag> STARTING_DECK = Colls.ulist(
		CardTag.ALL_NIGHTER,
		CardTag.ANXIETY,
		CardTag.APATHY,
		CardTag.BEFORE_MIDNIGHT,
		CardTag.BLUFF,
		CardTag.BRAG,
		CardTag.CAFFEINE,
		CardTag.CLOCK_OUT,
		CardTag.COPY,
		CardTag.CRAM,
		CardTag.CTRL_F,
		CardTag.DEFENESTRATE,
		CardTag.DISCIPLINE,
		CardTag.DIVIDE_AND_CONQUER,
		CardTag.DO_HOMEWORK,
		CardTag.EXCUSE,
		CardTag.FORESIGHT,
		CardTag.FREE_TIME,
		CardTag.GOOD_HABITS,
		CardTag.GRIND,
		CardTag.KEYBOARD_SHORTCUTS,
		CardTag.MEMORIZE,
		CardTag.MOTIVATIONAL_VIDEO,
		CardTag.NIHILISM,
		CardTag.NO_SLEEP_GANG,
		CardTag.ORGANIZE,
		CardTag.PACK_LUNCH,
		CardTag.PLANNER,
		CardTag.POMODORO,
		CardTag.PROCRASTINATE,
		CardTag.PUSH_THROUGH,
		CardTag.QUIZLET,
		CardTag.READ_FOR_FUN,
		CardTag.RESENTMENT,
		CardTag.REVIEW_NOTES,
		CardTag.SPIRITUAL_ENLIGHTENMENT,
		CardTag.STUDY,
		CardTag.SUGAR_RUSH,
		CardTag.TAKE_A_BREAK,
		CardTag.WRITE_NONSENSE,
		CardTag.YOGA
	);
	*/
	
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
