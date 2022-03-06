package visuals;

import java.util.*;

import base.*;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import visuals.animations.*;
import visuals.fxutils.*;
import visuals.hand.*;
import visuals.piles.DiscardPileLayer;
import visuals.ribbon.BottomRibbon;

public final class CardRepresentation extends StackPane implements Updatable {

	
	public static final double 
			WIDTH = 176, HEIGHT = WIDTH * 1.5,
			Y = BottomRibbon.Y - HEIGHT,
			ATTACK_X = GameScene.CENTER_X - WIDTH * .5,
			ATTACK_Y = 450;
	
	public static final Duration SCALE_DURATION = Duration.millis(500);
	
	private static final Duration
		FOCUS_DURATION = Duration.millis(400),
		FLY_BACK_DURATION = Duration.millis(500),
		FLY_TO_DISCARD_DURATION = Duration.millis(400),
		REMOVE_OT_DURATION = Duration.millis(500);
	private static final double FOCUS_Y = Y - 50, POISED_Y = FOCUS_Y - 50;
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
	
	private class FlyBackAnimation extends CardMoveAnimation {
		
		public FlyBackAnimation() {
			super(CardRepresentation.this, FLY_BACK_DURATION);
			setInterpolator(Interpolator.LINEAR);
			setFinish(CardRepresentation.this::flyBackFinished);
		}
	}
	
	private class EOTDiscardAnimation extends CardMoveAnimation {
		
		public EOTDiscardAnimation() {
			super(CardRepresentation.this, FLY_TO_DISCARD_DURATION);
			setInterpolator(Interpolator.LINEAR);
			setDest(DiscardPileLayer.CARD_X, DiscardPileLayer.CARD_Y);
			setFinish(CardRepresentation.this::eotDiscardFinished);
		}
		
	}
	
	private class RemoveOTAnimation extends FadeAnimation {
		
		public RemoveOTAnimation() {
			super(CardRepresentation.this, REMOVE_OT_DURATION, 1, 0);
			setFinish(CardRepresentation.this::removeOTFinished);
		}
		
	}
	
	private final Card card;
	private final Text name;
	private final List<Node> faceUpChildren, faceDownChildren;
	
	private State state;
	private boolean hovered, faceUp;
	private Animation cma;
	private Enemy target;
	
	private CardRepresentation(Card card) {
		this.card = card;
		name = new Text(card.tag().displayName());
		name.setFont(Fonts.UI_14_BOLD);
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		faceUpChildren = new ArrayList<>();
		Collections.addAll(faceUpChildren, new Sprite(Images.CARD_BASE), name);
		faceDownChildren = new ArrayList<>();
		Collections.addAll(faceDownChildren, new Sprite(Images.CARD_BACK));
		state = State.DOWN;
		hovered = false;
		setOnMouseEntered(e -> hoverEntered());
		setOnMouseExited(e -> hoverExited());
		setOnMousePressed(e -> mousePressed());
		setFaceUp();
	}
	
	public Card card() {
		return card;
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
		if(Vis.handLayer().hasSelected() && Vis.handLayer().selected() != this)
			return;
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = null;
 		if(state == State.BEING_PLAYED || state == State.FLYING_TO_DISCARD ||
 				state == State.FLYING_BACK || state == State.TO_POISED) {
			return; //you can't grab the card while it's flying back to your hand or while it's playing.
		}
		else if(state == State.FLYING) {
			if(Vis.mouseY() > MAX_RELEASE_Y) {
				startFlyBack();
			}
			else {
				requestStartBeingPlayed();
			}
		}
		else {
			Vis.handLayer().setSelected(this);
			if(card.isTargetted())
				startToPoised();
			else
				state = State.FLYING; //movement is handled in update(long).
		}
	}

	private void startFlyBack() {
		if(cma != null)
			Animation.manager().cancel(cma);
		Vis.handLayer().setSelected(null);
		setMouseTransparent(true);
		cma = new FlyBackAnimation().setStart().setDest(Vis.handLayer().xCoord(this), Y);
		Animation.manager().add(cma);
		state = State.FLYING_BACK;
	}
	
	private void flyBackFinished() {
		setMouseTransparent(false);
		state = State.DOWN;
	}
	
	private void requestStartBeingPlayed() {
		if(card.isLegal(null))
			startBeingPlayed();
		else
			startFlyBack();
	}

	private void startBeingPlayed() {
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new ScaleAnimation(SCALE_DURATION, this, .8);
		cma.setFinish(this::beingPlayedFinished);
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
		if(cma != null)
			Animation.manager().cancel(cma);
		Vis.handLayer().arrow().unbindAndHide();
		cma = new ToAttackAnimation().setStart();
		Animation.manager().add(cma);
		state = State.BEING_PLAYED;
	}
	
	private void beingPlayedFinished() {
		Vis.manager().playCardFromHand(card, target);
		target = null;
	}
	
	public void startEOTToDiscard() {
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new EOTDiscardAnimation().setStart();
		Animation.manager().add(cma);
		state = State.FLYING_TO_DISCARD;
	}
	
	private void eotDiscardFinished() {
		Vis.pileLayer().discard().addToTop(this);
		state = State.DOWN;
		Vis.manager().checkedResumeFromAnimation();
	}
	
	public void startExpandBackToNormalSize() {
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new ScaleAnimation(SCALE_DURATION, this, 1);
		cma.setFinish(this::expandBackToNormalFinished);
		Animation.manager().add(cma);
	}
	
	private void expandBackToNormalFinished() {
		state = State.DOWN;
	}
	
	private void startToPoised() {
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new ToPoisedAnimation().setStart().setDest(getLayoutX(), POISED_Y);
		state = State.TO_POISED;
		Vis.handLayer().setSelected(this);
		Animation.manager().add(cma);
		Vis.handLayer().arrow().displayFor(this);
	}
	
	public void cancelPoise() {
		if(state != State.TO_POISED)
			return;
		if(cma != null)
			Animation.manager().cancel(cma);
		Vis.handLayer().arrow().unbindAndHide();
		Vis.handLayer().setSelected(null);
		state = State.TO_DOWN;
		cma = new DownAnimation(FOCUS_DURATION).setStart().setDest(getLayoutX(), Y);
		Animation.manager().add(cma);
	}
	
	public void startRemoveOT() {
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new RemoveOTAnimation();
		Animation.manager().add(cma);
	}
	
	private void removeOTFinished() {
		this.state = State.DOWN;
		Vis.handLayer().removeOrThrow(this);
		Vis.handLayer().setSelected(null);
		Vis.manager().checkedResumeFromAnimation();
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
	
	private void setChildren(List<Node> children) {
		getChildren().clear();
		getChildren().addAll(children);
	}
	
	public State state() {
		return state;
	}
	
}
