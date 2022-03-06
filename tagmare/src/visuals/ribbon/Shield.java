package visuals.ribbon;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mechanics.Hub;
import visuals.Sprite;
import visuals.fxutils.*;

public class Shield extends StackPane {

	public static final double WIDTH = 64, HEIGHT = 64;
	public static final Font FONT = HealthBar.FONT;
	public static final Color TEXT_COLOR = HealthBar.TEXT_COLOR;
	
	private final Sprite icon;
	private final Label label;
	
	public Shield() {
		icon = new Sprite(Images.SHIELD);
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		label = Nodes.label(FONT, TEXT_COLOR);
		getChildren().addAll(icon, label);
		setVisible(false);
	}
	
	public Sprite icon() {
		return icon;
	}

	public void update() {
		int block = Hub.player().block().amount();
		if(block == 0)
			setVisible(false);
		else
			showBlock(block);
	}
	
	private void showBlock(int block) {
		label.setText(String.valueOf(block));
		setVisible(true);
	}
	
}
