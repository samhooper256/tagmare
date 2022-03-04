package visuals.hand;

import base.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.cards.*;
import visuals.*;
import visuals.animations.*;

/** Contains the {@link CardRepresentation CardRepresentations} for the player's {@link Hand}. */
public class HandLayer extends Pane implements Updatable {

	public static final double CARD_Y = GameScene.HEIGHT - CardRepresentation.HEIGHT;
	
	private static final double SPACING = 12;
	/** Each row is the x-coordinates for a given size. */
	private static final double[][] X_COORDS = new double[Hand.MAX_SIZE + 1][];
	private static final Duration
			CARD_DRAW_DURATION = Duration.seconds(.75),
			CARD_SHIFT_DURATION = CARD_DRAW_DURATION.multiply(2d / 3);
	
	static {
		for(int count = 0; count <= Hand.MAX_SIZE; count++) {
			double[] coords = new double[count];
			double width = count * CardRepresentation.WIDTH + SPACING * (count - 1);
			for(int i = 0; i < count; i++)
				coords[i] = (GameScene.WIDTH * .5 - width * .5) + CardRepresentation.WIDTH * i + SPACING * i;
			X_COORDS[count] = coords;
		}
	}
	private final Group cardGroup;
	
	private boolean addInProgress;
	private CardRepresentation flying;
	private Card cardBeingAdded;
	
	public HandLayer() {
		setPickOnBounds(false);
		cardGroup = new Group();
		getChildren().add(cardGroup);
	}
	
	@Override
	public void update(long diff) {
		for(Node n : cardGroup.getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	public void startAddCardToRightAnimation(Card card) {
		addInProgress = true;
		cardBeingAdded = card;
		CardRepresentation cr = CardRepresentation.of(card);
		cr.setFaceUp();
		cardGroup.getChildren().add(cr);
		int count = cardCountForWidth();
		double[] coords = X_COORDS[count];
		Animation.manager().add(new CardMoveAnimation(cr, CARD_DRAW_DURATION).setStart()
				.setDest(coords[count - 1], CARD_Y).setFinish(this::addFinisher));
		for(int i = 0; i < count - 1; i++) {
			Animation.manager().add(new CardMoveAnimation(getRepresentation(i), CARD_SHIFT_DURATION, Interpolator.SQRT)
					.setStart().setDest(coords[i], CARD_Y));
		}
	}
	
	private void addFinisher() {
		addInProgress = false;
		cardBeingAdded = null;
		VisualManager.get().checkedResumeFromAnimation();
	}
	
	private int cardCountForWidth() {
		return cardGroup.getChildren().size();
	}
	
	private CardRepresentation getRepresentation(int index) {
		return (CardRepresentation) cardGroup.getChildren().get(index);
	}
	
	public double xCoord(CardRepresentation cr) {
		int index = cardIndex(cr);
		return X_COORDS[cardCountForWidth()][index];
	}
	
	public int cardIndex(CardRepresentation cr) {
		return cardGroup.getChildren().indexOf(cr);
	}
	
	public boolean addInProgress() {
		return addInProgress;
	}
	
	public Card cardBeingAdded() {
		return cardBeingAdded;
	}
	
	public void setFlying(CardRepresentation cr) {
		if(cr != null && !cardGroup.getChildren().contains(cr))
			throw new IllegalArgumentException(String.format("Not in the HandLayer: %s", cr));
		flying = cr;
	}
	
	public boolean hasFlying() {
		return flying != null;
	}
	
	public CardRepresentation flying() {
		return flying;
	}
	
}
