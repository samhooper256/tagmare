package visuals.hand;

import base.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.cards.*;
import visuals.*;
import visuals.animations.*;

/** Contains the {@link CardRepresentation CardRepresentations} for the player's {@link Hand}. */
public class HandLayer extends Pane implements Updatable {

	public static final double CARD_Y = GameScene.HEIGHT - CardRepresentation.HEIGHT;
	
	private static final double SPACING = 12;
	private static final double[][] X_COORDS = new double[Hand.MAX_SIZE + 1][];
	private static final Duration
			CARD_DRAW_DURATION = Duration.seconds(.75),
			CARD_SHIFT_DURATION = CARD_DRAW_DURATION.multiply(2d / 3);
	
	private boolean addInProgress;
	private Card cardBeingAdded;
	
	public HandLayer() {
		for(int count = 0; count <= Hand.MAX_SIZE; count++) {
			double[] coords = new double[count];
			double width = count * CardRepresentation.WIDTH + SPACING * (count - 1);
			for(int i = 0; i < count; i++)
				coords[i] = (GameScene.WIDTH * .5 - width * .5) + CardRepresentation.WIDTH * i + SPACING * i;
			X_COORDS[count] = coords;
		}
	}
	
	@Override
	public void update(long diff) {
		for(Node n : getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	public void startAddCardToRightAnimation(Card card) {
		addInProgress = true;
		cardBeingAdded = card;
		CardRepresentation cr = CardRepresentation.of(card);
		cr.setFaceUp();
		getChildren().add(cr);
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
		VisualManager.get().checkedResume();
		addInProgress = false;
		cardBeingAdded = null;
	}
	
	private int cardCountForWidth() {
		return getChildren().size();
	}
	
	private CardRepresentation getRepresentation(int index) {
		return (CardRepresentation) getChildren().get(index);
	}
	
	public boolean addInProgress() {
		return addInProgress;
	}
	
	public Card cardBeingAdded() {
		return cardBeingAdded;
	}
	
}
