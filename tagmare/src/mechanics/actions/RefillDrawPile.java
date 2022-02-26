package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.*;

/** Called when the player runs out of cards from the draw pile and so has to shuffle all cards from their discard pile
 * (if any) and put them in their draw pile. Assumes the {@link DrawPile} {@link DrawPile#isEmpty() is empty}; throws
 * an {@link IllegalStateException} if not. */
public class RefillDrawPile extends AbstractAction {

	public RefillDrawPile() {
		super(null);
	}
	
	@Override
	public void execute() {
		DrawPile draw = Hub.drawPile();
		if(!draw.isEmpty())
			throw new IllegalStateException("Draw Pile is not empty");
		DiscardPile discard = Hub.discardPile();
		discard.shuffle();
		discard.transferTo(draw);
	}
	
}
