package visuals.combat.win;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utils.Nums;
import visuals.*;
import visuals.animations.*;

public class ChooseACard extends Label {

	public static final double BOTTOM_Y = RewardCard.Y - 60;

	public static final Duration
		INTRO_DURATION = WinLayer.SKIP_INTRO_DURATION,
		OUTRO_DURATION = WinLayer.SKIP_OUTRO_DURATION;
	
	private static final double INTRO_START_BOTTOM_Y = BOTTOM_Y + 20;
	
	private class Intro extends AbstractAnimation {
		
		public Intro() {
			super(INTRO_DURATION, Interpolator.SQUARED);
		}

		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, INTRO_START_BOTTOM_Y, BOTTOM_Y) - getHeight());
			setOpacity(frac);
		}
		
	}
	
	private class Outro extends AbstractAnimation {
		
		public Outro() {
			super(OUTRO_DURATION);
		}

		@Override
		public void interpolate(double frac) {
			setOpacity(1 - frac);
		}
		
	}
	
	public ChooseACard() {
		super("Choose a card");
		setFont(Fonts.GEORGIA_72_ITALIC);
		layoutXProperty().bind(widthProperty().multiply(-.5).add(GameScene.CENTER_X));
		setTextFill(Color.WHITE);
		setOpacity(0);
	}

	public void startIntro() {
		setOpacity(0);
		setLayoutY(INTRO_START_BOTTOM_Y - getHeight());
		Animation.manager().add(new Intro());
	}
	
	public void startOutro() {
		Animation.manager().add(new Outro());
	}
	
}
