package visuals.combat.enemies;

import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import mechanics.enemies.intents.Intent;
import visuals.Vis;
import visuals.animations.*;
import visuals.combat.enemies.intents.IntentRepresentation;

public class IntentContainer extends StackPane {

	private static final Duration TRANSITION_DURATION = Duration.millis(250);
	
	private class Transition extends AbstractAnimation {
		
		public Transition() {
			super(TRANSITION_DURATION);
			setFinish(this::finisher);
		}

		@Override
		public void interpolate(double frac) {
			bottom.setOpacity(frac);
			top.setOpacity(1 - frac);
		}
		
		private void finisher() {
			finishTransitionWithoutResume();
			Vis.manager().checkedResumeFromAnimation();
		}
		
	}
	
	private IntentRepresentation bottom, top;
	private Transition transition;
	
	public IntentContainer(Intent intent) {
		top = IntentRepresentation.of(intent);
		bottom = null;
		getChildren().add(top);
	}
	
	public void startTransition(Intent intent) {
		if(transitionInProgress())
			finishTransitionWithoutResume();
		bottom = IntentRepresentation.of(intent);
		bottom.setOpacity(0);
		getChildren().add(0, bottom);
		transition = new Transition();
		Animation.manager().add(transition);
	}
	
	private void finishTransitionWithoutResume() {
		Animation.manager().cancel(transition);
		bottom.setOpacity(1);
		getChildren().remove(top);
		top = bottom;
		bottom = null;
		transition = null;
	}
	
	public boolean transitionInProgress() {
		return transition != null;
	}
	
	/** Returns the current {@link Intent}. If transitioning, returns the old intent. */
	public Intent intent() {
		return top.intent();
	}
	
}
