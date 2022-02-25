package mechanics.actions;

import mechanics.Hub;
import mechanics.cards.*;
import mechanics.effects.DrawEffects;

/** Requests to draw a {@link Card} from the top of the {@link DrawPile}. It is a request because the {@link Hand} may
 * be {@link Hand#isFull() full}, in which case no cards can be drawn (and this {@link Action} does nothing). */
public class SimpleDrawRequest extends AbstractAction {

	public SimpleDrawRequest() {
		super(null);
	}

	@Override
	public void execute() {
		if(Hub.hand().isFull())
			return;
		Card card = Hub.drawPile().drawFromTop();
		Hub.hand().add(card);
		Hub.stack().pushReversed(DrawEffects.apply(card));
	}
	
}
