package visuals.combat.piles;

import javafx.scene.input.MouseEvent;
import mechanics.Hub;
import visuals.*;

public class DrawPileLayer extends PileSublayer {

	public static final double CARD_X = 100, CARD_Y = 100;
	
	public DrawPileLayer() {
		super(CARD_X, CARD_Y);
	}
	
	@Override
	protected void clicked(MouseEvent me) {
		Vis.drawPileGallery().startIntro(Hub.drawPile().sorted());
		me.consume(); //don't let it get passed to anything else.
	}
	
}
