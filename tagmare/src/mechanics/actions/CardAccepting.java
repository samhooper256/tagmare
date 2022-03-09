package mechanics.actions;

import java.util.List;

import mechanics.cards.Card;

/** {@link CardAccepting} {@link Action Actions} cannot {@link #canExecute() be executed} if they don't
 * {@link #hasCards() have cards}. */
public interface CardAccepting extends Action {

	/** Can only be called once. Throws an {@link IllegalStateException} if called more than once. */
	void setCards(List<Card> cards);
	
	/** The returned {@link List} <em>should not be modified</em>. The returned list may or may not be modifiable.
	 * Returns {@code null} if the cards have not been {@link #setCards(List) set}. */
	List<Card> cards();
	
	default boolean hasCards() {
		return cards() != null;
	}
	
	@Override
	default boolean canExecute() {
		return Action.super.canExecute() && hasCards();
	}
	
}
