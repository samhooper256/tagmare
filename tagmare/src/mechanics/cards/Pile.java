package mechanics.cards;

import java.util.*;

import utils.RNG;

public abstract class Pile {

	private List<Card> trueOrder;
	private SortedSet<Card> sorted;
	
	protected Pile() {
		this.trueOrder = new ArrayList<>();
		sorted = new TreeSet<>();
	}
	
	public void shuffle() {
		Collections.shuffle(trueOrder, RNG.SOURCE);
	}
	
	public void addToTop(Card card) {
		if(!sorted.add(card))
			throw new IllegalStateException(String.format("Duplicate card added: %s", card));
		trueOrder.add(card);
	}
	
	public void addToBottom(Card card) {
		if(!sorted.add(card))
			throw new IllegalStateException(String.format("Duplicate card added: %s", card));
		trueOrder.add(0, card);
	}
	
	/** The last {@link Card} in the {@link List} will be at the top of this {@link Pile} when this method returns. */
	public void addAllToTop(List<Card> list) {
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
	
}
