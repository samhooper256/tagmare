package visuals.hand;

import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.cards.*;
import utils.Nodes;
import visuals.*;
import visuals.animations.*;

/** Contains the {@link CardRepresentation CardRepresentations} for the player's {@link Hand}. */
public class HandLayer extends Pane {

	private static final double SPACING = 12, CARD_Y = GameScene.HEIGHT - CardRepresentation.HEIGHT;
	private static final double[][] X_COORDS = new double[Hand.MAX_SIZE + 1][];
	
	static {
		
	}
	
	public HandLayer() {
		for(int count = 0; count <= Hand.MAX_SIZE; count++) {
			double[] coords = new double[count];
			double width = count * CardRepresentation.WIDTH + SPACING * (count - 1);
			for(int i = 0; i < count; i++)
				coords[i] = (GameScene.WIDTH * .5 - width * .5) + CardRepresentation.WIDTH * i + SPACING * i;
			X_COORDS[count] = coords;
		}
	}
	
	public void startAddCardToRightAnimation(Card card, Runnable onFinish) {
		CardRepresentation cr = CardRepresentation.of(card);
		Nodes.setLayout(cr, 0, 0);
		getChildren().add(cr);
		Animation.manager().add(new CardMoveAnimation(cr, Duration.seconds(2)).setStart(0, 0).setDest(500, 500)
				.withFinisher(onFinish));
	}
	
	private void addCardToRight(Card card) {
		getChildren().add(CardRepresentation.of(card));
		int count = cardCountForWidth();
		double width = count * CardRepresentation.WIDTH + SPACING * (count - 1);
		for(int i = 0; i < count; i++) {
			double x = (GameScene.WIDTH * .5 - width * .5) + CardRepresentation.WIDTH * i + SPACING * i;
			CardRepresentation cr = getRepresentation(i);
			cr.setLayoutX(x);
			cr.setLayoutY(CARD_Y);
		}
	}
	
	private int cardCountForWidth() {
		return getChildren().size();
	}
	
	private CardRepresentation getRepresentation(int index) {
		return (CardRepresentation) getChildren().get(index);
	}
	
}
