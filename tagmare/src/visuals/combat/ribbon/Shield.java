package visuals.combat.ribbon;

import java.util.function.DoubleUnaryOperator;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import mechanics.Hub;
import utils.Nums;
import visuals.*;
import visuals.animations.*;
import visuals.combat.enemies.BlockIndicator;
import visuals.fxutils.*;

public class Shield extends StackPane {

	//don't change visibility; only adjust opacity
	
	public static final double WIDTH = 64, HEIGHT = 64;
	public static final Font FONT = Fonts.NUMBERS_30_BOLD;
	public static final Color TEXT_COLOR = HealthBar.TEXT_COLOR;
	public static final String CSS = "player-shield";
	
	private static final double INTRO_Y = -10, OUTRO_Y = 10;
	private static final Duration
		VISIBILITY_DURATION = BlockIndicator.VISIBILITY_DURATION,
		NUMERIC_DURATION = BlockIndicator.NUMERIC_DURATION;
	private static final double OUTRO_MOVE_X = -30, OUTRO_MOVE_Y = 15, FALL_ROTATION = -30;
	private static final DoubleUnaryOperator
		FALL_X = t -> OUTRO_MOVE_X * t,
		FALL_Y = t -> 2 * OUTRO_MOVE_Y * t * t - OUTRO_MOVE_Y * t;
	
	private class Intro extends AbstractAnimation {
		
		private final int dest;
		
		public Intro(int dest) {
			super(VISIBILITY_DURATION);
			this.dest = dest;
			setOpacity(0);
			setLayoutY(INTRO_Y);
			top.setText(String.valueOf(dest));
			setFinish(this::finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, INTRO_Y, 0));
			setOpacity(frac);
		}

		private void finisher() {
			showing = dest;
			tryResume();
		}
		
	}
	
	private class SmoothOutro extends AbstractAnimation {
	
		public SmoothOutro() {
			super(VISIBILITY_DURATION);
			setFinish(this::finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, 0, OUTRO_Y));
			setOpacity(1 - frac);
		}

		private void finisher() {
			showing = 0;
			tryResume();
		}
		
	}
	
	private class FallOff extends AbstractAnimation {
	
		public FallOff() {
			super(VISIBILITY_DURATION);
			setFinish(this::finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			setOpacity(1 - frac);
			setRotate(Nums.lerp(frac, 0, FALL_ROTATION));
			Nodes.setLayout(Shield.this, anchorX + FALL_X.applyAsDouble(frac), FALL_Y.applyAsDouble(frac));
		}

		private void finisher() {
			showing = 0;
			tryResume();
		}
		
	}
	
	private class Transition extends AbstractAnimation {
		
		private final int dest;
		
		public Transition(int dest) {
			super(NUMERIC_DURATION);
			this.dest = dest;
			bottom.setText(String.valueOf(dest));
			setFinish(this::finisher);
		}

		@Override
		public void interpolate(double frac) {
			bottom.setOpacity(frac);
			top.setOpacity(1 - frac);
		}
		
		private void finisher() {
			showing = dest;
			top.setText(String.valueOf(dest));
			bottom.setOpacity(0);
			top.setOpacity(1);
			tryResume();
		}
		
	}
	
	private final Sprite icon;
	private final Label top, bottom;
	
	private boolean resume;
	private int showing;
	private double anchorX;
	
	public Shield() {
		icon = new Sprite(Images.PLAYER_SHIELD);
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		top = Nodes.label(FONT, TEXT_COLOR);
		bottom = Nodes.label(FONT, TEXT_COLOR);
		resume = false;
		getChildren().addAll(icon, bottom, top);
		getStyleClass().add(CSS);
		hide();
	}
	
	public Sprite icon() {
		return icon;
	}

	public void update() {
		int block = Hub.player().block().amount();
		if(block == 0)
			hide();
		else
			showBlock(block);
	}
	
	/** The shield will be knocked off (instead of smoothly falling/fading out) if the new block is zero. */
	public void startTransition(boolean resume) {
		this.resume = resume;
		int b = Hub.player().block().amount();
		transitionPrep();
		if(hasChanged())
			if(showing() == 0)
				Animation.manager().add(new Intro(b));
			else if(b == 0)
				Animation.manager().add(new FallOff());
			else
				Animation.manager().add(new Transition(b));
		else
			tryResume();
	}
	
	/** Will finish instantly if didn't change.
	 * @throws IllegalStateException if new block is not zero. */
	public void startSmoothFall(boolean resume) {
		this.resume = resume;
		int b = Hub.player().block().amount();
		transitionPrep();
		if(b != 0)
			throw new IllegalStateException("Dest block is not 0");
		if(hasChanged())
			Animation.manager().add(new SmoothOutro());
		else
			tryResume();
	}
	
	private void transitionPrep() {
		setRotate(0);
		setLayoutX(anchorX);
	}
	
	private void tryResume() {
		if(resume)
			Vis.manager().checkedResumeFromAnimation();
	}
	
	public void setAnchorX(double anchorX) {
		this.anchorX = anchorX;
		setLayoutX(anchorX);
	}

	public double secToAnimateChange() {
		if(!hasChanged())
			return 0;
		if(showing() == 0 || Hub.player().block().amount() == 0)
			return VISIBILITY_DURATION.toSeconds();
		return NUMERIC_DURATION.toSeconds();
	}
	
	/** (acutal - showing)*/
	public int change() {
		return Hub.player().block().amount() - showing();
	}

	/** Updates after an animation finishes. */
	public int showing() {
		return showing;
	}
	
	public boolean hasChanged() {
		return change() != 0;
	}
	
	private void showBlock(int block) {
		top.setText(String.valueOf(block));
		setOpacity(1);
	}
	
	public void hide() {
		setOpacity(0);
		top.setText("0");
	}
	
}
