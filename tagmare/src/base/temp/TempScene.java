package base.temp;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import mechanics.enemies.Enemy;
import visuals.fxutils.Nodes;

public class TempScene extends Scene {

	public static final TempScene INSTANCE = new TempScene();
	
	public static TempScene get() {
		return INSTANCE;
	}
	
	public final Pane pane;
	public final CardListDisplay deckDisplay, drawPileDisplay, discardPileDisplay;
	public final HandDisplay handDisplay;
	public final EnergyDisplay energyDisplay;
	public final EnemyBar enemyBar;
	public final ButtonBar buttonBar;
	public final BlockDisplay blockDisplay;
	public final HealthDisplay healthDisplay;
	public final EModifierDisplay emodifierDisplay;
	public final PModifierDisplay pmodifierDisplay;
	public final Hi hi;
	
	private TempScene() {
		super(new Pane(), 800, 400);
		this.pane = (Pane) getRoot();
		deckDisplay = new DeckDisplay();
		handDisplay = new HandDisplay();
		drawPileDisplay = new DrawPileDisplay();
		discardPileDisplay = new DiscardPileDisplay();
		energyDisplay = new EnergyDisplay();
		enemyBar = new EnemyBar();
		buttonBar = new ButtonBar();
		blockDisplay = new BlockDisplay();
		healthDisplay = new HealthDisplay();
		emodifierDisplay = new EModifierDisplay();
		emodifierDisplay.layoutXProperty().bind(emodifierDisplay.widthProperty().multiply(-.5).add(widthProperty().multiply(.5)));
		emodifierDisplay.setLayoutY(100);
		pmodifierDisplay = new PModifierDisplay();
		hi = new Hi();
		Nodes.setLayout(energyDisplay, 0, 50);
		Nodes.setLayout(enemyBar, 0, 40);
		enemyBar.prefWidthProperty().bind(widthProperty());
		Nodes.setLayout(buttonBar, 0, 340);
		buttonBar.prefWidthProperty().bind(widthProperty());
		Nodes.setLayout(deckDisplay, 40, 100);
		Nodes.setLayout(drawPileDisplay, 200, 100);
		Nodes.setLayout(handDisplay, 360, 100);
		Nodes.setLayout(discardPileDisplay, 360 + 160, 100);
		Nodes.setLayout(blockDisplay, 340, 310);
		Nodes.setLayout(healthDisplay, 340, 330);
		Nodes.setLayout(hi, 200, 30);
		Nodes.setLayout(pmodifierDisplay, 10, 390);
		pane.getChildren().addAll(energyDisplay, enemyBar, buttonBar, deckDisplay, drawPileDisplay, handDisplay,
				discardPileDisplay, blockDisplay, healthDisplay, hi, emodifierDisplay, pmodifierDisplay);
		updateAll();
	}
	
	public void updateAll() {
		deckDisplay.update();
		drawPileDisplay.update();
		handDisplay.update();
		discardPileDisplay.update();
		energyDisplay.update();
		enemyBar.update();
		buttonBar.update();
		blockDisplay.update();
		healthDisplay.update();
		emodifierDisplay.update();
		pmodifierDisplay.update();
	}
	
	public void showModifiers(Enemy enemy) {
		emodifierDisplay.update(enemy);
		emodifierDisplay.setVisible(true);
	}
	
	public void hideModifiers() {
		emodifierDisplay.setVisible(false);
	}
	
}
