package visuals;

import java.util.*;

import base.Updatable;
import javafx.scene.Node;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import visuals.animations.*;
import visuals.combat.hand.*;
import visuals.combat.ribbon.BottomRibbon;
import visuals.fxutils.*;

public final class CardRepresentation extends AbstractCardRepresentation implements Updatable {
	
	public static final double 
			Y = BottomRibbon.Y - HEIGHT,
			ATTACK_X = GameScene.CENTER_X - WIDTH * .5, ATTACK_Y = 450,
			TOP_X = GameScene.CENTER_X - WIDTH * .5, TOP_Y = 20;
	public static final Duration SCALE_DURATION = Duration.millis(500);
	
	private static final Duration
		FOCUS_DURATION = Duration.millis(400),
		FLY_BACK_DURATION = Duration.millis(500),
		REMOVE_OT_DURATION = Duration.millis(500),
		TO_TOP_DURATION = Duration.millis(500),
		BYPASS_INTRO_DURATION = Duration.millis(500);
	private static final double FOCUS_Y = Y - 50, POISED_Y = FOCUS_Y - 50, DEST_PLAY_SCALE = .8;
	/** If a {@link State#FLYING} card is released above this y (that is, when the mouse's y-coordinate is less than
	 * this value), the card is played. Otherwise, the card returns to the hand and is not played.*/
	private static final double MAX_RELEASE_Y = FOCUS_Y;
	private static final WeakHashMap<Card, CardRepresentation> MAP = new WeakHashMap<>();
	
	public static CardRepresentation of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new CardRepresentation(card));
		return MAP.get(card);
	}
	
	public enum State {
		DOWN, TO_UP, UP, TO_DOWN, TO_POISED, FLYING, FLYING_BACK, BEING_PLAYED, FLYING_TO_DISCARD;
	}

	private class DownAnimation extends CardMoveAnimation {

		public DownAnimation(Duration duration) {
			super(CardRepresentation.this, duration);
			setInterpolator(Interpolator.bow(-1));
			setFinish(CardRepresentation.this::downFinished);
			setReverseFinish(CardRepresentation.this::downReverseFinished);
		}
		
	}
	
	private class UpAnimation extends CardMoveAnimation {

		public UpAnimation(Duration duration) {
			super(CardRepresentation.this, duration, Interpolator.bow(1));
			setFinish(CardRepresentation.this::upFinished);
			setReverseFinish(CardRepresentation.this::upReverseFinished);
		}
		
	}
	
	private class ToPoisedAnimation extends CardMoveAnimation {
		
		public ToPoisedAnimation() {
			super(CardRepresentation.this, FOCUS_DURATION, Interpolator.bow(1));
		}
		
	}
	
	private class ToAttackAnimation extends CardMoveAnimation {
		
		public ToAttackAnimation() {
			super(CardRepresentation.this, SCALE_DURATION, Interpolator.LINEAR);
			setDest(ATTACK_X, ATTACK_Y);
			setFinish(CardRepresentation.this::beingPlayedFinished);
		}
	}
	
	private class FlyBackToHandAnimation extends CardMoveAnimation {
		
		public FlyBackToHandAnimation() {
			super(CardRepresentation.this, FLY_BACK_DURATION);
			setInterpolator(Interpolator.LINEAR);
			setFinish(CardRepresentation.this::flyBackToHandFinished);
		}
	}
	
	private class RemoveOTAnimation extends FadeAnimation {
		
		public RemoveOTAnimation() {
			super(CardRepresentation.this, REMOVE_OT_DURATION, 1, 0);
			setFinish(CardRepresentation.this::removeOTFinished);
		}
		
	}
	
	private class ToTopAnimation extends CardMoveAnimation {
		
		public ToTopAnimation() {
			super(CardRepresentation.this, TO_TOP_DURATION);
			setDest(TOP_X, TOP_Y);
		}
	}
	
	private class BypassIntroAnimation extends AbstractAnimation {

		public BypassIntroAnimation() {
			super(BYPASS_INTRO_DURATION);
			setFinish(CardRepresentation.this::bypassFinished);
		}
		
		@Override
		public void interpolate(double frac) {
			setOpacity(frac);
			Nodes.setScale(CardRepresentation.this, 1 - frac + frac * DEST_PLAY_SCALE);
		}
		
	}
	
	private final List<Node> faceDownChildren;
	
	private State state;
	private boolean hovered, faceUp;
	private Animation cma;
	private Enemy target;
	
	private CardRepresentation(Card card) {
		super(card);
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		faceDownChildren = new ArrayList<>();
		Collections.addAll(faceDownChildren, new Sprite(Images.CARD_BACK));
		state = State.DOWN;
		hovered = false;
		faceUp = true;
		setOnMouseEntered(e -> hoverEntered());
		setOnMouseExited(e -> hoverExited());
		setOnMousePressed(e -> mousePressed());
	}
	
	@Override
	public void update(long diff) {
		if(!canAnimate())
			return;
		if(state == State.FLYING) {
			Nodes.setLayout(this, Vis.mouseX() - WIDTH * .5, Vis.mouseY() - HEIGHT * .5);
		}
		else if(state == State.TO_POISED) {
			return;
		}
		else if(Vis.handLayer().hasSelected()) {
			return;
		}
		if(hovered && state == State.DOWN) {
			cma = new UpAnimation(FOCUS_DURATION).setStart().setDest(getLayoutX(), FOCUS_Y);
			state = State.TO_UP;
			Animation.manager().add(cma);
		}
		else if(hovered && state == State.TO_DOWN) {
			state = State.TO_UP;
			cma.setRate(cma instanceof UpAnimation ? 1 : -1);
		}
		else if(!hovered && state == State.TO_UP) {
			state = State.TO_DOWN;
			cma.setRate(cma instanceof UpAnimation ? -1 : 1);
		}
		else if(!hovered && state == State.UP) {
			cma = new DownAnimation(FOCUS_DURATION).setStart().setDest(getLayoutX(), Y);
			state = State.TO_DOWN;
			Animation.manager().add(cma);
		}
	}

	private void mousePressed() {
		if(Vis.inquiryActive()) {
			Vis.inquiryLayer().clickedCardFromHand(card);
			return;
		}
		if(	(Vis.handLayer().addInProgress()) ||
			(Vis.handLayer().hasSelected() && Vis.handLayer().selected() != this))
			return;
		cancelAnimation();
		cma = null;
 		if(state == State.BEING_PLAYED || state == State.FLYING_TO_DISCARD ||
 				state == State.FLYING_BACK || state == State.TO_POISED) {
			return; //you can't grab the card while it's flying back to your hand or while it's playing.
		}
		else if(state == State.FLYING) {
			if(Vis.mouseY() > MAX_RELEASE_Y) {
				startFlyBackToHand();
			}
			else {
				requestStartBeingPlayed();
			}
		}
		else {
			if(card.isTargetted())
				startToPoised();
			else
				state = State.FLYING; //movement is handled in update(long).
			Vis.handLayer().setSelected(this);
		}
	}

	private void startFlyBackToHand() {
		cancelAnimation();
		Vis.handLayer().setSelected(null);
		setMouseTransparent(true);
		cma = new FlyBackToHandAnimation().setStart().setDest(Vis.handLayer().xCoord(this), Y);
		Animation.manager().add(cma);
		state = State.FLYING_BACK;
	}
	
	private void flyBackToHandFinished() {
		setMouseTransparent(false);
		state = State.DOWN;
	}
	
	private void requestStartBeingPlayed() {
		if(card.isLegal(null))
			startBeingPlayed();
		else
			startFlyBackToHand();
	}

	private void startBeingPlayed() {
		cancelAnimation();
		cma = new ScaleAnimation(SCALE_DURATION, this, DEST_PLAY_SCALE);
		cma.setFinish(this::beingPlayedFinished);
		Vis.handLayer().transferToPlayGroup(this);
		Vis.handLayer().startReorganize();
		Animation.manager().add(cma);
		state = State.BEING_PLAYED;
	}
	
	public void requestStartBeingPlayed(Enemy target) {
		if(card.isLegal(target))
			startBeingPlayed(target);
	}
	
	/** @param target must not be {@code null}. */
	private void startBeingPlayed(Enemy target) {
		if(!card.isTargetted())
			throw new IllegalStateException(String.format("Not a targetted card: %s", card));
		this.target = Objects.requireNonNull(target);
		cancelAnimation();
		Vis.handLayer().arrow().unbindAndHide();
		Vis.handLayer().transferToPlayGroup(this);
		Vis.handLayer().startReorganize();
		cma = new ToAttackAnimation().setStart();
		Animation.manager().add(cma);
		state = State.BEING_PLAYED;
	}
	
	private void beingPlayedFinished() {
		Vis.manager().playCardFromHand(card, target);
		target = null;
	}
	
	public void startBeingBypassPlayed() {
		cancelAnimation();
		setOpacity(0);
		Nodes.setLayout(this, GameScene.CENTER_X - WIDTH * .5, GameScene.CENTER_Y - HEIGHT * .5);
		if(!Vis.handLayer().playGroup().getChildren().add(this))
			throw new IllegalStateException(String.format("Already in playGroup: %s", this));
		Vis.handLayer().addToCardsInPlay(card);
		cma = new BypassIntroAnimation();
		Animation.manager().add(cma);
		state = State.BEING_PLAYED;
	}
	
	private void bypassFinished() {
		Vis.manager().checkedResumeFromAnimation();
	}
	
	public void startExpandBackToNormalSize() {
		cancelAnimation();
		cma = new ScaleAnimation(SCALE_DURATION, this, 1);
		cma.setFinish(this::expandBackToNormalFinished);
		Animation.manager().add(cma);
	}
	
	private void expandBackToNormalFinished() {
		state = State.DOWN;
	}
	
	private void startToPoised() {
		cancelAnimation();
		cma = new ToPoisedAnimation().setStart().setDest(getLayoutX(), POISED_Y);
		state = State.TO_POISED;
		Vis.handLayer().setSelected(this);
		Animation.manager().add(cma);
		Vis.handLayer().arrow().displayFor(this);
	}
	
	public void cancelPoise() {
		if(state != State.TO_POISED)
			return;
		cancelAnimation();
		Vis.handLayer().arrow().unbindAndHide();
		Vis.handLayer().setSelected(null);
		state = State.TO_DOWN;
		cma = new DownAnimation(FOCUS_DURATION).setStart().setDest(getLayoutX(), Y);
		Animation.manager().add(cma);
	}
	
	public void startRemoveOT() {
		cancelAnimation();
		cma = new RemoveOTAnimation();
		Animation.manager().add(cma);
		Vis.handLayer().startReorganize();
	}
	
	private void removeOTFinished() {
		this.state = State.DOWN;
		Vis.handLayer().removeFromInPlayOrThrow(card);
		Vis.handLayer().removeOrThrow(this);
		Vis.manager().checkedResumeFromAnimation();
	}
	
	public void startFlyToTop() {
		if(state != State.BEING_PLAYED)
			throw new IllegalStateException(String.format("State is %s", state));
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new ToTopAnimation().setStart();
		Animation.manager().add(cma);
	}
	
	public void cancelAnimation() {
		if(cma != null)
			Animation.manager().cancel(cma);
	}
	
	private boolean canAnimate() {
		return Hub.stack().isEmpty();
	}

	private void upFinished() {
		state = State.UP;
	}
	
	private void upReverseFinished() {
		state = State.DOWN;
	}
	
	private void downFinished() {
		state = State.DOWN;
	}
	
	private void downReverseFinished() {
		state = State.UP;
	}
	
	private void hoverEntered() {
		hovered = true;
	}
	
	private void hoverExited() {
		hovered = false;
	}

	public boolean isFaceUp() {
		return faceUp;
	}
	
	public boolean isFaceDown() {
		return !faceUp;
	}
	
	public void setFaceUp() {
		if(!getChildren().isEmpty() && isFaceUp())
			return;
		faceUp = true;
		setChildren(faceUpChildren);
	}
	
	public void setFaceDown() {
		if(!getChildren().isEmpty() && isFaceDown())
			return;
		faceUp = false;
		setChildren(faceDownChildren);
	}
	
	public State state() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return String.format("CR[%s]", card);
	}
	
	public void debugPrint(String prefix) {
		System.out.printf("%sCardRepresentation for card=%s%n", prefix, card);
		System.out.printf("%sstate=%s%n", prefix, state);
		System.out.printf("%shovered=%s%n", prefix, hovered);
		System.out.printf("%scma=%s%n", prefix, cma);
		System.out.printf("%starget=%s%n", prefix, target);
	}
	
}
