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
	
	public int size() {
		return cards.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean isFull() {
		return size() == MAX_SIZE;
	}

	@Override
	public Iterator<Card> iterator() {
		return Iterators.unmodifiable(cards.iterator());
	}
	
}
