package visuals.gallery;

import java.util.*;

import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.cards.Card;
import utils.Nums;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.Nodes;

public class Gallery extends Pane {

	private static final int CARDS_PER_ROW = 7;

	private static final Duration INTRO_DURATION = Duration.millis(500), OUTRO_DURATION = INTRO_DURATION;
	
	public static final double LEFT_X = 100, TOP_Y = 120, TIP_Y = TOP_Y - 40, DESCRIPTION_Y = TOP_Y - 80;
	private static final double
		RIGHT_X = GameScene.WIDTH - LEFT_X,
		VERTICAL_SEPARATION = 50,
		BOTTOM_MARGIN = DESCRIPTION_Y,
		SCROLL_MULTIPLIER = 5,
		ANIMATION_OFFSET = 15;
	
	private static final double[] X_COORDS = new double[CARDS_PER_ROW];
	
	static {
		double dist = ((RIGHT_X - LEFT_X) - GalleryCard.WIDTH) / (CARDS_PER_ROW - 1);
		for(int i = 0; i < X_COORDS.length; i++)
			X_COORDS[i] = LEFT_X + i * dist;
	}

	/** 0-based index of the card in this {@link Gallery}. */
	private static double getX(int index) {
		return X_COORDS[index % CARDS_PER_ROW];
	}
	
	/** 0-based index of the card in this {@link Gallery}. */
	private static double getY(int index) {
		return (index / CARDS_PER_ROW) * (AbstractCardRepresentation.HEIGHT + VERTICAL_SEPARATION);
	}
	
	private class Intro extends AbstractAnimation {
		
		public Intro() {
			super(INTRO_DURATION);
			setFinish(this::finisher);
		}

		@Override
		public void interpolate(double frac) {
			setOpacity(frac);
			setYOffsetUnclamped(Nums.lerp(frac, ANIMATION_OFFSET, 0));
		}
		
		private void finisher() {
			introInProgress = false;
		}
		
	}
	
	private class Outro extends AbstractAnimation {
		
		public Outro() {
			super(OUTRO_DURATION);
			setFinish(this::finisher);
		}

		@Override
		public void interpolate(double frac) {
			setOpacity(1 - frac);
			setYOffsetUnclamped(Nums.lerp(frac, 0, ANIMATION_OFFSET));
		}

		private void finisher() {
			setMouseTransparent(true);
			runCloseAction();
		}
		
	}
	
	private final GalleryGlass glass;
	private final Group cardGroup;
	private final GalleryDescription description;
	private final OrderTip tip;
	
	private Runnable closeAction;
	private boolean introInProgress;
	private double yOffset;
	
	public Gallery() {
		this("");
	}
	
	public Gallery(String description) {
		this(description, "");
	}
	
	public Gallery(String description, String additionalTipText) {
		this(description, additionalTipText, null);
	}
	
	/** Any additional text should be punctuated and should not start with leading whitespace.
	 * Pass an empty string for no additional text. */
	public Gallery(String description, String additionalTipText, Runnable closeAction) {
		glass = new GalleryGlass();
		glass.setOnMouseClicked(me -> glassClicked());
		tip = new OrderTip(additionalTipText);
		cardGroup = new Group(); //y is set whenever startIntro() is called.
		this.description = new GalleryDescription(description);
		this.closeAction = closeAction;
		introInProgress = false;
		yOffset = 0;
		getChildren().addAll(glass, tip, this.description, cardGroup);
		setOpacity(0);
		setMouseTransparent(true);
		setOnScroll(this::scrolled);
	}

	public void setCards(Iterable<Card> cards) {
		setCards(cards.iterator());
	}
	
	public void setCards(Iterator<Card> itr) {
		cardGroup.getChildren().clear();
		int i = 0;
		while(itr.hasNext()) {
			GalleryCard gc = GalleryCard.of(itr.next());
			Nodes.setLayout(gc, getX(i), getY(i));
			cardGroup.getChildren().add(gc);
			i++;
		}
		description.setTextFor(i);
	}
	
	public void startIntro(Iterable<Card> cards) {
		setCards(cards);
		startIntro();
	}
	
	/** Assumes {@link #setCards(List)} has already been called <em>OR</em> an overridden version of this method
	 * has called {@link #setCards(Iterable)} before calling {@code super.startIntro()}. */
	public void startIntro() {
		setMouseTransparent(false);
		setOpacity(0);
		setYOffset(0);
		introInProgress = true;
		Animation.manager().add(new Intro());
	}

	private void glassClicked() {
		if(!introInProgress)
			startOutro();
	}
	
	private void startOutro() {
		Animation.manager().add(new Outro());
	}

	private void scrolled(ScrollEvent se) {
		if(scrollingAllowed())
			setYOffset(yOffset + SCROLL_MULTIPLIER * se.getDeltaY());
	}
	
	private void setYOffset(double yo) {
		yo = Nums.clamp(yo, minYOffset(), 0);
		setYOffsetUnclamped(yo);
	}

	private void setYOffsetUnclamped(double yo) {
		yOffset = yo;
		description.setLayoutY(DESCRIPTION_Y + yOffset);
		tip.setLayoutY(TIP_Y + yOffset);
		cardGroup.setLayoutY(TOP_Y + yOffset);
	}
	
	private void runCloseAction() {
		if(closeAction != null)
			closeAction.run();
	}
	
	/** Returns {@code this} {@link Gallery}. */
	public Gallery setCloseAction(Runnable closeAction) {
		this.closeAction = closeAction;
		return this;
	}
	
	/** The y-coordinate of cards on the bottommost row when this {@link Gallery} is fully scrolled up. */
	public double bottomY() {
		int rc = rowCount();
		return TOP_Y + rc * AbstractCardRepresentation.HEIGHT + (rc - 1) * VERTICAL_SEPARATION;
	}
	
	public int rowCount() {
		return (cardCount() - 1) / CARDS_PER_ROW + 1;
	}
	
	private int cardCount() {
		return description.cardCount(); //just use GalleryDescription's cardCount property instead of making a
		//new one here.
	}
	
	private boolean scrollingAllowed() {
		return bottomY() + BOTTOM_MARGIN > GameScene.HEIGHT;
	}
	
	private double minYOffset() {
		return Math.min(0, GameScene.HEIGHT - bottomY() - BOTTOM_MARGIN);
	}
	
	public void setDescription(String description) {
		this.description.setDescription(description);
	}
	
	public void setAdditionalTipText(String additionalTipText) {
		tip.setAdditionalTipText(additionalTipText);
	}
	
}
