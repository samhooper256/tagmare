package visuals.combat.win;

import java.util.Arrays;

import base.Updatable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.combat.CombatReward;
import visuals.animations.*;
import visuals.combat.win.RewardCard.Position;

public class WinLayer extends Pane implements Updatable {
	
	private static final Duration
		INTRO_DURATION = Duration.seconds(1),
		DELAY_TO_CARD_INTRO = Duration.millis(300),
		DELAY_TO_SKIP_INTRO = RewardCard.INTRO_1_DURATION.add(RewardCard.INTRO_DELAY).add(Duration.millis(150)),
		SKIP_INTRO_DURATION = RewardCard.INTRO_2_DURATION.subtract(Duration.millis(150)),
		SKIP_OUTRO_DURATION = Duration.millis(300);
	
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
	
	private class DelayedSkipIntro extends DelayedAction {
		
		public DelayedSkipIntro() {
			super(DELAY_TO_SKIP_INTRO);
			setFinish(this::finisher);
		}
		
		private void finisher() {
			Animation.manager().add(new SkipIntro());
		}
		
	}
	
	private class SkipIntro extends AbstractAnimation {
		
		public SkipIntro() {
			super(SKIP_INTRO_DURATION);
		}

		@Override
		public void interpolate(double frac) {
			skipArrow.setOpacity(frac);
			skipLabel.setOpacity(frac);
		}
		
	}
	
	private class SkipOutro extends AbstractAnimation {
		
		public SkipOutro() {
			super(SKIP_OUTRO_DURATION);
		}
		
		@Override
		public void interpolate(double frac) {
			skipArrow.setOpacity(1 - frac);
			skipLabel.setOpacity(1 - frac);
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
		setPickOnBounds(true);
		hide();
	}
	
	public void startCardReward() {
		setMouseTransparent(false);
		getChildren().clear();
		CombatReward reward = Hub.combat().reward();
		left = new RewardCard(reward.get(0), Position.LEFT);
		center = new RewardCard(reward.get(1), Position.CENTER);
		right = new RewardCard(reward.get(2), Position.RIGHT);
		skipArrow.setOpacity(0);
		skipLabel.setOpacity(0);
		getChildren().addAll(darkGlass, skipArrow, skipLabel, left, right, center);
		Animation.manager().addAll(new Intro(), new DelayedCardIntro(), new DelayedSkipIntro());
	}
	
	public void selectAndExit(RewardCard rc) {
		if(!getChildren().contains(rc))
			throw new IllegalArgumentException(String.format("Not in this WinLayer: %s", rc));
		for(RewardCard o : Arrays.asList(left, center, right))
			if(o != rc)
				o.startDownOutro();
		rc.startUpOutro();
		Animation.manager().add(new SkipOutro());
	}
	
	@Override
	public void update(long diff) {
		for(Node n : getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	public void hide() {
		setOpacity(0);
		setMouseTransparent(true);
	}
	
}
