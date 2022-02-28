package mechanics.actions;

import mechanics.*;
import mechanics.effects.SOTEffects;

/** An {@link Action} that generates all {@link SOTEffects} (via {@link SOTEffects#apply()}) and pushes them to the
 * {@link ActionStack}. */
public class GenerateSOT extends AbstractAction {

	public GenerateSOT() {
		super(null);
	}
	
	@Override
	public void execute() {
		Hub.stack().pushReversed(SOTEffects.apply());
	}
	
}
