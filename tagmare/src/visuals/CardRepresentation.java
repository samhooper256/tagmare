package visuals;

import java.util.*;

import base.*;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.cards.Card;
import visuals.animations.*;
import visuals.fxutils.*;
import visuals.hand.*;
import visuals.piles.DiscardPileLayer;

public final class CardRepresentation extends StackPane implements Updatable {

	public static final double WIDTH = 176, HEIGHT = WIDTH * 1.5;
	
	private static final Duration
		FOCUS_DURATION = Duration.millis(400),
		FLY_BACK_DURATION = Duration.millis(500),
		FLY_TO_DISCARD_DURATION = Duration.millis(500);
	private static final double FOCUS_Y = GameScene.HEIGHT - HEIGHT - 50;
	/** If a {@link State#FLYING} card is released above this y (that is, when the mouse's y-coordinate is less than
	 * this value), the card is played. Otherwise, the card returns to the hand and is not played.*/
	private static final double MAX_RELEASE_Y = FOCUS_Y;
	private static final WeakHashMap<Card, CardRepresentation> MAP = new WeakHashMap<>();
	
	public static CardRepresentation of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new CardRepresentation(card));
		return MAP.get(card);
	}
	
	enum State {
		DOWN, TO_UP, UP, TO_DOWN, FLYING, FLYING_BACK, BEING_PLAYED, FLYING_TO_DISCARD;
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
			super(CardRepresentation.this, duration);
			setInterpolator(Interpolator.bow(1));
			setFinish(CardRepresentation.this::upFinished);
			setReverseFinish(CardRepresentation.this::upReverseFinished);
		}
		
	}
	
	private class FlyBackAnimation extends CardMoveAnimation {
		
		public FlyBackAnimation() {
			super(CardRepresentation.this, FLY_BACK_DURATION);
			setInterpolator(Interpolator.LINEAR);
			setFinish(CardRepresentation.this::flyBackFinished);
		}
	}
	
	private class FlyToDiscardAnimation extends CardMoveAnimation {
		
		public FlyToDiscardAnimation() {
			super(CardRepresentation.this, FLY_TO_DISCARD_DURATION);
			setInterpolator(Interpolator.LINEAR);
			setDest(DiscardPileLayer.CARD_X, DiscardPileLayer.CARD_Y);
			setFinish(CardRepresentation.this::flyToDiscardFinished);
		}
		
	}
	
	private final Card card;
	private final Text name;
	private final List<Node> faceUpChildren, faceDownChildren;
	
	private State state;
	private boolean hovered, faceUp;
	private Animation cma;
	
	private CardRepresentation(Card card) {
		this.card = card;
		name = new Text(card.tag().displayName());
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
		else if(Vis.handLayer().hasFlying()) {
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
			cma = new DownAnimation(FOCUS_DURATION).setStart().setDest(getLayoutX(), HandLayer.CARD_Y);
			state = State.TO_DOWN;
			Animation.manager().add(cma);
		}
	}

	private void mousePressed() {
		if(Vis.handLayer().hasFlying() && Vis.handLayer().flying() != this)
			return;
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = null;
 		if(state == State.BEING_PLAYED || state == State.FLYING_TO_DISCARD || state == State.FLYING_BACK) {
			return; //you can't grab the card while it's flying back to your hand or while it's playing.
		}
		else if(state == State.FLYING) {
			if(Vis.mouseY() > MAX_RELEASE_Y) {
				startFlyBack();
			}
		}
		else {
			Vis.handLayer().setFlying(this);
			state = State.FLYING;
		}
	}

	private void startFlyBack() {
		if(cma != null)
			Animation.manager().cancel(cma);
		Vis.handLayer().setFlying(null);
		cma = new FlyBackAnimation().setStart().setDest(Vis.handLayer().xCoord(this), HandLayer.CARD_Y);
		Animation.manager().add(cma);
		state = State.FLYING_BACK;
	}
	
	private void flyBackFinished() {
		state = State.DOWN;
	}
	
	public void startFlyToDiscard() {
		if(cma != null)
			Animation.manager().cancel(cma);
		cma = new FlyToDiscardAnimation().setStart();
		Animation.manager().add(cma);
		state = State.FLYING_TO_DISCARD;
	}
	
	private void flyToDiscardFinished() {
		Vis.pileLayer().discard().addToTop(this);
		state = State.DOWN;
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
	
}
