package visuals.piles;

import javafx.scene.layout.Pane;
import visuals.*;
import visuals.fxutils.Nodes;

public class DiscardPileLayer extends Pane {

	public static final double CARD_X = GameScene.WIDTH - 100 - CardRepresentation.WIDTH, CARD_Y = 100;
	
	public DiscardPileLayer() {
		
	}
	
	public void addToTop(CardRepresentation cr) {
		Nodes.setLayout(cr, CARD_X, CARD_Y);
		cr.setFaceDown();
		getChildren().add(cr);
	}
	
}