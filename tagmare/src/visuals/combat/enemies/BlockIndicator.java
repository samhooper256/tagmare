package visuals.combat.enemies;

import java.util.function.DoubleUnaryOperator;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import mechanics.Block;
import mechanics.enemies.Enemy;
import utils.Nums;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.*;

public class BlockIndicator extends StackPane {
	
	//leave it visible at all times; only mess with the opacity.
	
	public static final double SIZE = 32;
	public static final Font FONT = Fonts.UI_24_BOLD;
	public static final Color TEXT_COLOR = Color.WHITE;
	public static final String CSS = "block-indicator";
	
	public static final Duration VISIBILITY_DURATION = Duration.millis(500), NUMERIC_DURATION = Duration.millis(300);
	private static final double ANIMATION_Y = -10, OUTRO_ROTATION = -30, OUTRO_MOVE_X = -10, OUTRO_MOVE_Y = 10;
	private static final DoubleUnaryOperator OUTRO_X = t -> OUTRO_MOVE_X * t, OUTRO_Y = t -> OUTRO_MOVE_Y * t * t;
	
	private class Intro extends AbstractAnimation {

		private final int dest;
		
		public Intro(int dest) {
			super(VISIBILITY_DURATION);
			setOpacity(0);
			setLayoutY(ANIMATION_Y);
			this.dest = dest;
			top.setText(String.valueOf(dest));
			setFinish(this::finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, ANIMATION_Y, 0));
			setOpacity(frac);
		}
		
		private void finisher() {
			showing = dest;
			commonFinisher();
		}
		
	}
	
	private class Outro extends AbstractAnimation {
	
		public Outro() {
			super(VISIBILITY_DURATION);
			setFinish(this::finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			setOpacity(1 - frac);
			setRotate(Nums.lerp(frac, 0, OUTRO_ROTATION));
			Nodes.setLayout(BlockIndicator.this, anchorX + OUTRO_X.applyAsDouble(frac), OUTRO_Y.applyAsDouble(frac));
		}


		private void finisher() {
			showing = 0;
			commonFinisher();
		}
		
	}
	
	private class Transition extends AbstractAnimation {

		private final int dest;
		
		public Transition(int dest) {
			super(NUMERIC_DURATION);
			this.dest = dest;
			bottom.setOpacity(0);
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
			commonFinisher();
		}
		
	}
	
	private final Sprite sprite;
	private final Label top, bottom;
	private final Block block;
	
	private double anchorX;
	private int showing;
	private boolean transitionInProgress, resume;
	
	public BlockIndicator(Enemy enemy) {
		sprite = new Sprite(Images.ENEMY_SHIELD);
		top = Nodes.label(FONT, TEXT_COLOR);
		bottom = Nodes.label(FONT, TEXT_COLOR);
		bottom.setOpacity(0);
		this.block = enemy.block();
		showing = 0;
		transitionInProgress = resume = false;
		setOpacity(0);
		getChildren().addAll(sprite, bottom, top);
		getStyleClass().add(CSS);
	}
	
	public void startTransition(boolean resume) {
		if(transitionInProgress)
			throw new IllegalStateException("Transition already in progress");
		this.resume = resume;
		int dest = block().amount();
		setLayoutX(anchorX);
		setRotate(0);
		transitionInProgress = true;
		if(showing() == 0 && dest > 0) {
			Animation.manager().add(new Intro(dest));
		}
		else if(showing() > 0 && dest == 0) {
			Animation.manager().add(new Outro());
		}
		else if(showing() != dest) {
			Animation.manager().add(new Transition(dest));
		}
		else {
			transitionInProgress = false;
			update();
			if(resume)
				Vis.manager().checkedResumeFromAnimation();
		}
	}
	
	public double secToAnimateChange() {
		if(!hasChanged())
			return 0;
		if(showing() == 0 || block().amount() == 0)
			return VISIBILITY_DURATION.toSeconds();
		else
			return NUMERIC_DURATION.toSeconds();
	}
	
	public void update() { //TODO remove this method eventually.
		int b = block().amount();
		if(b == 0) {
			setOpacity(0);
		}
		else {
			showing = b;
			top.setText(String.valueOf(showing));
			setOpacity(1);
		}
	}
	
	/** Executing at the end of every animation's finisher. */
	private void commonFinisher() {
		transitionInProgress = false;
		if(resume)
			Vis.manager().checkedResumeFromAnimation();
	}
	
	public void setAnchorX(double anchorX) {
		setLayoutX(anchorX);
		this.anchorX = anchorX;
	}
	
	/** The current block number showing. Only updates when a transition finishes. */
	public int showing() {
		return showing;
	}
	
	/** Returns (actual - showing). */
	public int change() {
		return block().amount() - showing();
	}
	
	public boolean hasChanged() {
		return change() != 0;
	}
	
	public boolean transitionInProgress() {
		return transitionInProgress;
	}
	
	public Block block() {
		return block;
	}
}
