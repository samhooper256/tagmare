package visuals.calendar;

import javafx.util.Duration;
import visuals.Vis;
import visuals.animations.*;
import visuals.eye.Eye;

public class CalendarEye extends Eye {

	private static final Duration CLOSE_HOLD = Duration.millis(500);
	
	public CalendarEye() {
		
	}

	public void startTransition() {
		startTransition(() -> {
			Vis.manager().startNextCombat();
			Animation.manager().add(new DelayedAction(CLOSE_HOLD, () -> proceedInTransition()));
		});
	}
	
}
