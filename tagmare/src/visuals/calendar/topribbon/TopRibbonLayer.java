package visuals.calendar.topribbon;

import javafx.scene.layout.Pane;
import visuals.*;
import visuals.fxutils.*;

public class TopRibbonLayer extends Pane {

	public static final double HEIGHT = Images.CALENDAR_TOP_RIBBON.getHeight();

	private static final double VIEW_DECK_X = 800, VIEW_DECK_Y = 20;
	
	private final ViewDeck viewDeck;
	
	public TopRibbonLayer() {
		Sprite ribbon = new Sprite(Images.CALENDAR_TOP_RIBBON);
		viewDeck = new ViewDeck();
		Nodes.setLayout(viewDeck, VIEW_DECK_X, VIEW_DECK_Y);
		getChildren().addAll(ribbon, viewDeck);
	}
	
	public ViewDeck viewDeck() {
		return viewDeck;
	}
	
}
