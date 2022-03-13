package visuals.combat.win;

import base.Updatable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.combat.CombatReward;
import visuals.animations.*;
import visuals.combat.win.RewardCard.Position;

public class WinLayer extends Pane implements Updatable {
	
	private static final Duration INTRO_DURATION = Duration.seconds(1), DELAY_TO_CARD_INTRO = Duration.millis(300);
	
	private class Intro extends AbstractAnimation {
	
		public Intro() {
			super(INTRO_DURATION);
		}

		@Override
		public void interpolate(double frac) {
			setOpacity(frac);
		}
		
	}
	
	private class DelayedCardIntro extends DelayedAction {

		public DelayedCardIntro() {
			super(DELAY_TO_CARD_INTRO);
			setFinish(this::finisher);
		}

		private void finisher() {
			left.startIntro();
			center.startIntro();
			right.startIntro();
		}
		
	}
	
	private final DarkGlass darkGlass;
	private final SkipArrow skipArrow;
	private final SkipLabel skipLabel;
	
	private RewardCard left, center, right;
	
	public WinLayer() {
		darkGlass = new DarkGlass();
		skipArrow = new SkipArrow();
		skipLabel = new SkipLabel();
		setOpacity(0);
		setMouseTransparent(true);
		setPickOnBounds(true);
	}
	
	public void startCardReward() {
		setMouseTransparent(false);
		getChildren().clear();
		CombatReward reward = Hub.combat().reward();
		left = new RewardCard(reward.get(0), Position.LEFT);
		center = new RewardCard(reward.get(1), Position.CENTER);
		right = new RewardCard(reward.get(2), Position.RIGHT);
		getChildren().addAll(darkGlass, skipArrow, skipLabel, left, right, center);
		Animation.manager().addAll(new Intro(), new DelayedCardIntro());
	}
	
	@Override
	public void update(long diff) {
		for(Node n : getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
}
