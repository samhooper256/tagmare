package visuals.combat.win;

import base.Updatable;
import javafx.util.Duration;
import mechanics.cards.*;
import utils.Nums;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.Nodes;
import visuals.tooltips.*;

public class RewardCard extends AbstractCardRepresentation implements Updatable, TooltipAware {
	
	public static final Duration
		INTRO_1_DURATION = Duration.millis(600),
		INTRO_DELAY = Duration.millis(150),
		INTRO_2_DURATION = Duration.millis(600),
		OUTRO_DURATION = Duration.millis(600);
	
	public static final double Y = GameScene.CENTER_Y - HEIGHT * .5;
	
	/** Distance from {@link GameScene#CENTER_X} to the center of the {@link Position#LEFT} (or {@link Position#RIGHT})
	 * card. */
	private static final double CENTER_DIST = 300;
	private static final double SCALE_MAX = 1.1, SCALE_VELOCIY = .5;
	
	public enum Position {
		LEFT, CENTER, RIGHT;
		
		double x() {
			switch(this) {
				case LEFT: return GameScene.CENTER_X - CENTER_DIST - WIDTH * .5;
				case CENTER: return GameScene.CENTER_X - WIDTH * .5;
				case RIGHT: return GameScene.CENTER_X + CENTER_DIST - WIDTH * .5;
			}
			throw new UnsupportedOperationException(String.format("Position: %s", this));
		}
	}
	
	private class IntroPhase1 extends AbstractAnimation {
		
		public IntroPhase1() {
			super(INTRO_1_DURATION, Interpolator.SQRT);
			setFinish(RewardCard.this::startIntroDelay);
		}

		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, GameScene.HEIGHT, Y));
		}
		
	}
	
	private class IntroDelay extends DelayedAction {
		
		public IntroDelay() {
			super(INTRO_DELAY);
			setFinish(RewardCard.this::startIntroPhase2);
		}
		
	}
	
	private class IntroPhase2 extends AbstractAnimation {
		
		public IntroPhase2() {
			super(INTRO_2_DURATION, Interpolator.SQRT);
			setFinish(RewardCard.this::introFinisher);
		}

		@Override
		public void interpolate(double frac) {
			setLayoutX(Nums.lerp(frac, Position.CENTER.x(), position().x()));
		}
		
	}
	
	private class UpOutro extends AbstractAnimation {
		
		public UpOutro() {
			super(OUTRO_DURATION);
		}

		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, Y, -HEIGHT));
		}

	}
	
	private class DownOutro extends AbstractAnimation {
		
		public DownOutro() {
			super(OUTRO_DURATION);
		}

		@Override
		public void interpolate(double frac) {
			setLayoutY(Nums.lerp(frac, Y, GameScene.HEIGHT));
		}

	}

	private final Position position;
	private final CardTooltipManager cardTooltipManager;
	
	private boolean hovered, introInProgres, clicked;
	
	public RewardCard(Card card, Position position) {
		super(card);
		setVisible(false);
		this.position = position;
		introInProgres = hovered = clicked = false;
		cardTooltipManager = CardTooltipManager.install(this);
		cardTooltipManager.setShowCondition(this::tooltipShowCondition);
		setOnMouseClicked(me -> mouseClicked());
		setOnMouseEntered(me -> hoverEntered());
		setOnMouseExited(me -> hoverExited());
	}

	private boolean tooltipShowCondition() {
		return !introInProgres && !clicked;
	}
	
	public void startIntro() {
		Nodes.setLayout(this, Position.CENTER.x(), GameScene.HEIGHT);
		introInProgres = true;
		hovered = false;
		setVisible(true);
		Animation.manager().add(new IntroPhase1());
	}
	
	private void startIntroDelay() {
		Animation.manager().add(new IntroDelay());
	}
	
	private void startIntroPhase2() {
		Animation.manager().add(new IntroPhase2());
	}
	
	private void introFinisher() {
		introInProgres = false;
	}
	
	public void startUpOutro() {
		Animation.manager().add(new UpOutro());
	}
	
	public void startDownOutro() {
		Animation.manager().add(new DownOutro());
	}
	
	@Override
	public void update(long diff) {
		if(introInProgres)
			return;
		double s = getScaleX(), sec = diff / 1e9;
		if(hovered && s < SCALE_MAX)
			Nodes.setScale(this, Math.min(SCALE_MAX, s + SCALE_VELOCIY * sec));
		else if(!hovered && s > 1)
			Nodes.setScale(this, Math.max(1, s - SCALE_VELOCIY * sec));
	}
	
	private void mouseClicked() {
		if(introInProgres || clicked)
			return;
		clicked = true;
		tooltipManager().hide();
		Vis.winLayer().selectAndExit(this);
	}
	
	private void hoverEntered() {
		hovered = true;
	}
	
	private void hoverExited() {
		hovered = false;
	}
	
	public Position position() {
		return position;
	}

	@Override
	public CardTooltipManager tooltipManager() {
		return cardTooltipManager;
	}
	
}
