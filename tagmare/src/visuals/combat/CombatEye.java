package visuals.combat;

import javafx.util.Duration;
import visuals.Vis;
import visuals.animations.*;
import visuals.eye.Eye;

public class CombatEye extends Eye {

	private static final Duration CLOSE_HOLD = Duration.millis(500);
	
	public void startTransition() {
		startTransition(() -> {
			Vis.manager().exitWonCombat();
			Animation.manager().add(new DelayedAction(CLOSE_HOLD, () -> proceedInTransition()));
		});
	}
	
}
