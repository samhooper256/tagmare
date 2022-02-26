package base.temp;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.Nodes;

public class GameScene extends Scene {

	public static final GameScene INSTANCE = new GameScene();
	
	public final Pane pane;
	public final CardListDisplay deckDisplay, drawPileDisplay, discardPileDisplay;
	public final HandDisplay handDisplay;
	public final EnergyDisplay energyDisplay;
	public final EnemyBar enemyBar;
	public final ButtonBar buttonBar;
	
	private GameScene() {
		super(new Pane(), 800, 400);
		this.pane = (Pane) getRoot();
		deckDisplay = new DeckDisplay();
		handDisplay = new HandDisplay();
		drawPileDisplay = new DrawPileDisplay();
		discardPileDisplay = new DiscardPileDisplay();
		energyDisplay = new EnergyDisplay();
		enemyBar = new EnemyBar();
		buttonBar = new ButtonBar();
		Nodes.setLayout(energyDisplay, 0, 50);
		Nodes.setLayout(enemyBar, 0, 40);
		enemyBar.prefWidthProperty().bind(widthProperty());
		Nodes.setLayout(buttonBar, 0, 340);
		buttonBar.prefWidthProperty().bind(widthProperty());
		Nodes.setLayout(deckDisplay, 40, 100);
		Nodes.setLayout(drawPileDisplay, 200, 100);
		Nodes.setLayout(handDisplay, 360, 100);
		Nodes.setLayout(discardPileDisplay, 360 + 160, 100);
		pane.getChildren()
			.addAll(energyDisplay, enemyBar, buttonBar, deckDisplay, drawPileDisplay, handDisplay, discardPileDisplay);
		updateAll();
	}
	
	public void updateAll() {
		deckDisplay.update();
		drawPileDisplay.update();
		handDisplay.update();
		discardPileDisplay.update();
		energyDisplay.update();
		enemyBar.update();
	}
	
	
}
