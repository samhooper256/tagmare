package base;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.Nodes;

public class GameScene extends Scene {

	public final Pane pane;
	public final DeckDisplay deckDisplay;
	
	public GameScene() {
		super(new Pane(), 800, 400);
		this.pane = (Pane) getRoot();
		this.deckDisplay = new DeckDisplay();
		Nodes.setLayout(deckDisplay, 40, 100);
		pane.getChildren().add(deckDisplay);
	}
	
}
