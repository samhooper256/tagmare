package visuals.combat.enemies;

import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import mechanics.enemies.Enemy;
import mechanics.enemies.intents.Intent;
import visuals.Vis;
import visuals.animations.*;
import visuals.combat.enemies.intents.IntentRepresentation;
import visuals.fxutils.Nodes;

public class IntentContainer extends StackPane {

	public static final double HEIGHT = IntentRepresentation.HEIGHT;
	
	private static final Duration TRANSITION_DURATION = Duration.millis(500);
	
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
		Nodes.setAllHeights(this, HEIGHT);
		getChildren().add(top);
	}
	
	/** Transitions to the enemy's current {@link Enemy#intent() intent}.*/
	public void startTransition(Enemy enemy) {
		if(transitionInProgress())
			finishTransitionWithoutResume();
		bottom = IntentRepresentation.of(enemy.intent());
		bottom.update(enemy);
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
