package visuals.combat.piles;

import javafx.scene.input.MouseEvent;
import mechanics.Hub;
import visuals.*;

public class DiscardPileLayer extends PileSublayer {

	public static final double CARD_X = GameScene.WIDTH - 100 - CardRepresentation.WIDTH, CARD_Y = 100;
	
	public DiscardPileLayer() {
		super(CARD_X, CARD_Y);
	}

	@Override
	protected void clicked(MouseEvent me) {
		Vis.discardPileGallery().startIntro(Hub.discardPile());
		me.consume(); //don't let it get passed to anything else.
	}
	
}