package mechanics.cards;

import java.util.*;

import utils.Iterators;

/** {@link #iterator()} is unmodifiable. */
public class Hand implements Iterable<Card> {

	public static final int MAX_SIZE = 10;

	private final List<Card> cards;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	
	/** Simply adds the given {@link Card} to this {@link Hand}; does not cause any downstream effects (e.g. draw
	 * effects) to occur. Throws an {@link IllegalStateException} if {@link #isFull()}. */
	public void add(Card card) {
		if(isFull())
			throw new IllegalStateException(String.format("Full hand: %s", this));
		cards.add(card);
	}
	
	/** Removes the {@link Card} from this {@link Hand}.
	 * @throws IllegalArgumentException if {@code card} is not in this hand. */
	public void removeOrThrow(Card card) {
		if(!cards.remove(card))
			throw new IllegalArgumentException(String.format("Card is not in hand: %s", card));
	}
	
	public boolean contains(Card card) {
		return cards.contains(card);
	}
	
	public int size() {
		return cards.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean isFull() {
		return size() == MAX_SIZE;
	}

	public Card get(int index) {
		return cards.get(index);
	}
	
	public int spaceRemaining() {
		return MAX_SIZE - size();
	}
	
	/** Unmodifiable. */
	public List<Card> cards() {
		return Collections.unmodifiableList(cards);
	}
	
	@Override
	public Iterator<Card> iterator() {
		return Iterators.unmodifiable(cards.iterator());
	}
	
	@Override
	public String toString() {
		return String.format("Hand%s", cards);
	}
	
}
