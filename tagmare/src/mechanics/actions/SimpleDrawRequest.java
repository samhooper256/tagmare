package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.*;
import mechanics.effects.DrawEffects;

/** Requests to draw a {@link Card} from the top of the {@link DrawPile}. It is a request because the {@link Hand} may
 * be {@link Hand#isFull() full}, in which case no cards can be drawn (and this {@link Action} does nothing). If the
 * draw pile is {@link Pile#isEmpty() empty} and the {@link DiscardPile} is not, adds another {@link SimpleDrawRequest}
 * action to the stack, then adds {@link RefillDrawPile} action <em>on top</em> of that one. If both the draw and
 * discard piles are empty, does nothing. */
public class SimpleDrawRequest extends AbstractAction {

	public SimpleDrawRequest() {
		super(null);
	}

	@Override
	public void execute() {
		if(Hub.hand().isFull())
			return;
		DrawPile draw = Hub.drawPile();
		DiscardPile discard = Hub.discardPile();
		if(draw.isEmpty() && discard.isEmpty())
			return;
		if(draw.isEmpty()) {
			Hub.stack().push(new SimpleDrawRequest());
			Hub.stack().push(new RefillDrawPile());
		}
		else {
			Card card = draw.drawFromTop();
			Hub.hand().add(card);
			Hub.stack().pushReversed(DrawEffects.apply(card));
		}
		
	}
	
}
