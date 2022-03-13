package visuals.combat.win;

import java.util.Arrays;

import base.Updatable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.combat.CombatReward;
import utils.Nums;
import visuals.Vis;
import visuals.animations.*;
import visuals.combat.win.RewardCard.Position;

public class WinLayer extends Pane implements Updatable {
	
	public static final Duration
		INTRO_DURATION = Duration.seconds(1),
		DELAY_TO_CARD_INTRO = Duration.millis(300),
		DELAY_TO_SKIP_INTRO = Duration.millis(900),
		DELAY_TO_CHOOSE_INTRO = Duration.millis(900),
		SKIP_INTRO_DURATION = RewardCard.INTRO_2_DURATION,
		SKIP_OUTRO_DURATION = Duration.millis(300),
		DELAY_TO_EYE = RewardCard.OUTRO_DURATION.subtract(Duration.millis(100));
	
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
			super(SKIP_INTRO_DURATION, Interpolator.SQUARED);
		}

		@Override
		public void interpolate(double frac) {
			skipArrow.setOpacity(frac);
			skipLabel.setOpacity(frac);
			skipArrow.setLayoutY(Nums.lerp(frac, SkipArrow.INTRO_START_Y, SkipArrow.Y));
			skipLabel.setLayoutY(Nums.lerp(frac, SkipLabel.INTRO_START_Y, SkipLabel.Y));
		}
		
	}
	
	private class DelayedChooseIntro extends DelayedAction {

		public DelayedChooseIntro() {
			super(DELAY_TO_CHOOSE_INTRO);
			setFinish(this::finisher);
		}

		private void finisher() {
			chooseACard.startIntro();
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
	
	private class DelayedEyeTransition extends DelayedAction {
		
		public DelayedEyeTransition() {
			super(DELAY_TO_EYE);
			setFinish(this::finisher);
		}
		
		private void finisher() {
			Vis.combatEye().startTransition();
		}
		
	}
	
	private final DarkGlass darkGlass;
	private final ChooseACard chooseACard;
	private final SkipArrow skipArrow;
	private final SkipLabel skipLabel;
	private RewardCard left, center, right;
	
	public WinLayer() {
		darkGlass = new DarkGlass();
		chooseACard = new ChooseACard();
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
		skipArrow.setLayoutY(SkipArrow.INTRO_START_Y);
		skipLabel.setLayoutY(SkipLabel.INTRO_START_Y);
		getChildren().addAll(darkGlass, chooseACard, skipArrow, skipLabel, left, right, center);
		Animation.manager().addAll(new Intro(), new DelayedCardIntro(), new DelayedSkipIntro(),
				new DelayedChooseIntro());
	}
	
	public void selectAndExit(RewardCard rc) {
		if(!getChildren().contains(rc))
			throw new IllegalArgumentException(String.format("Not in this WinLayer: %s", rc));
		for(RewardCard o : Arrays.asList(left, center, right))
			if(o != rc)
				o.startDownOutro();
		rc.startUpOutro();
		chooseACard.startOutro();
		Animation.manager().addAll(new SkipOutro(), new DelayedEyeTransition());
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
