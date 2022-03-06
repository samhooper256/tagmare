package visuals.ribbon;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mechanics.*;
import visuals.*;
import visuals.fxutils.*;

public class HealthBar extends Pane {

	public static final double HEIGHT = BottomRibbon.HEIGHT, RADIUS = 6;
	public static final Font FONT = Fonts.UI_30;
	public static final Color TEXT_COLOR = Color.WHITE;
	
	private final double width;
	private final Region filled, back;
	private final Label hp, max;
	
	public HealthBar(double width) {
		setLayoutX(GameScene.CENTER_X - width * .5);
		this.width = width;
		filled = new StackPane();
		filled.setBackground(Backgrounds.rounded(Color.RED, RADIUS));
		Nodes.setPrefAndMaxSize(filled, width, HEIGHT);
		back = new StackPane();
		back.setBackground(Backgrounds.rounded(Color.DARKRED, RADIUS));
		Nodes.setPrefAndMaxSize(back, width, HEIGHT);
		hp = createHP();
		max = createMax();
		updateHPandMax();
		getChildren().addAll(back, filled, hp, max);
		Nodes.setPrefAndMaxSize(this, width, HEIGHT);
	}

	public void update() {
		updateHPandMax();
	}
	
	public void updateHPandMax() {
		Health health = Hub.player().health();
		Nodes.setPrefAndMaxWidth(filled, health.proportion() * width);
		hp.setText(String.valueOf(health.hp()));
		max.setText(String.valueOf(health.max()));
	}
	
	private Label createHP() {
		Label hp = Nodes.label(FONT, TEXT_COLOR);
		hp.layoutYProperty().bind(hp.heightProperty().multiply(-.5).add(HEIGHT * .5));
		return hp;
	}
	
	private Label createMax() {
		Label max = Nodes.label(FONT, TEXT_COLOR);
		max.layoutYProperty().bind(max.heightProperty().multiply(-.5).add(HEIGHT * .5));
		max.layoutXProperty().bind(max.widthProperty().multiply(-1).add(width));
		return max;
	}
	
}