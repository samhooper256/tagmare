package visuals;

import java.util.WeakHashMap;

import base.Updatable;
import base.temp.Backgrounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.cards.Card;
import visuals.animations.Animation;
import visuals.fxutils.Nodes;
import visuals.hand.*;

public final class CardRepresentation extends StackPane implements Updatable {

	public static final double WIDTH = 176, HEIGHT = WIDTH * 1.5;
	
	private static final Duration FOCUS_DURATION = Duration.seconds(1);
	private static final double FOCUS_Y = GameScene.HEIGHT - HEIGHT - 100;
	private static final WeakHashMap<Card, CardRepresentation> MAP = new WeakHashMap<>();
	
	public static CardRepresentation of(Card card) {
		if(!MAP.containsKey(card))
			MAP.put(card, new CardRepresentation(card));
		return MAP.get(card);
	}
	
	enum State {
		DOWN, TO_UP, UP, TO_DOWN;
	}

	private final Card card;
	private final Text name;
	
	private State state;
	private boolean hovered;
	private Animation cma;
	
	private CardRepresentation(Card card) {
		this.card = card;
		name = new Text(card.tag().displayName());
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		setBackground(Backgrounds.of(Color.BISQUE));
		getChildren().addAll(name);
		setOnMouseEntered(eh -> hoverEntered());
		setOnMouseExited(eh -> hoverExited());
		state = State.DOWN;
		hovered = false;
	}
	
	public Card card() {
		return card;
	}
	
	@Override
	public void update(long diff) {
		if(!canAnimate()) {
			return;
		}
		else {
			if(hovered && state == State.DOWN) {
				cma = new CardMoveAnimation(this, FOCUS_DURATION).setStart().setDest(getLayoutX(), FOCUS_Y)
						.setFinish(this::upFinished).setReverseFinish(this::upReverseFinished);
				state = State.TO_UP;
				Animation.manager().add(cma);
			}
			else if(hovered && state == State.TO_DOWN) {
				state = State.TO_UP;
				cma.setRate(-1); //we're going down right now, so reverse it so we go back up.
			}
			else if(!hovered && state == State.TO_UP) {
				state = State.TO_DOWN;
				cma.setRate(-1); //we're going up right now, so reverse it so we go back down.
			}
			else if(!hovered && state == State.UP) {
				cma = new CardMoveAnimation(this, FOCUS_DURATION).setStart().setDest(getLayoutX(), HandLayer.CARD_Y)
						.setFinish(this::downFinished).setReverseFinish(this::downReverseFinished);
				state = State.TO_DOWN;
				Animation.manager().add(cma);
			}
		}
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
		System.out.printf("enter hover: %s%n", this);
		hovered = true;
	}
	
	private void hoverExited() {
		System.out.printf("exit hover: %s%n", this);
		hovered = false;
	}
	
}
