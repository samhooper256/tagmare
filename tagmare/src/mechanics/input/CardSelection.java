package mechanics.input;

import java.util.List;

import mechanics.cards.Card;

/** Determines if a {@link List} of {@link Card Cards} satisfies a certain condition. Immutable. */
public interface CardSelection {

	boolean validate(List<Card> cards);

}