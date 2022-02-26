package mechanics.cards;

import java.util.*;

import utils.*;

/** The iterator is unmodifiable and iterates through the {@link #trueOrder()} of the cards. */
public abstract class Pile implements Iterable<Card> {

	private List<Card> trueOrder;
	private List<Card> sorted;
	
	protected Pile() {
		this.trueOrder = new ArrayList<>();
		sorted = new ArrayList<>();
	}
	
	public void shuffle() {
		Collections.shuffle(trueOrder, RNG.SOURCE);
	}
	
	public void addToTop(Card card) {
		addToSorted(card);
		trueOrder.add(card);
	}
	
	public void addToBottom(Card card) {
		addToSorted(card);
		trueOrder.add(0, card);
	}
	
	/** The last {@link Card} in the {@link List} will be at the top of this {@link Pile} when this method returns. */
	public void addAllToTop(Iterable<Card> list) {
		for(Card card : list)
			addToTop(card);
	}

	/** Throws an {@link IllegalStateException} if {@link #isEmpty()}.*/
	public Card drawFromTop() {
		if(isEmpty())
			throw new IllegalStateException("Empty");
		Card c = trueOrder.remove(trueOrder.size() - 1);
		sorted.remove(c);
		return c;
	}
	
	/** throws {@link IllegalStateException} if the {@link Card} is already in this pile. */
	private void addToSorted(Card card) {
		if(sorted.contains(card))
			throw new IllegalStateException(String.format("Duplicate card added: %s", card));
		sorted.add(card);
	}
	
	/** Adds all the cards in this {@link Pile} to the {@link #addAllToTop(Iterable) top} of the given one.
	 * {@link #clear() Clears} {@code this} pile.
	 * @throws IllegalArgumentException if {@code (dest == this)}. */
	public void transferTo(Pile dest) {
		if(dest == this)
			throw new IllegalArgumentException("Cannot transfer to self");
		dest.addAllToTop(this);
		clear();
	}
	
	public int size() {
		return trueOrder.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void clear() {
		trueOrder.clear();
		sorted.clear();
	}
	
	/** Unmodifiable. Updated dynamically. The cards are in order from the bottom of the pile to the top. */
	public List<Card> trueOrder() {
		return Collections.unmodifiableList(trueOrder);
	}
	
	@Override
	public Iterator<Card> iterator() {
		return Iterators.unmodifiable(trueOrder.iterator());
	}
	
	@Override
	public String toString() {
		StringJoiner j = new StringJoiner(", ");
		for(Card c : trueOrder)
			j.add(c.toString());
		return String.format("%s[trueOrder=%s]", getClass().getSimpleName(), j);
	}
	
}
