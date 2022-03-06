package visuals.ribbon;

import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import mechanics.Hub;
import visuals.Sprite;
import visuals.fxutils.*;

public class Shield extends StackPane {

	public static final double WIDTH = 64, HEIGHT = 64;
	public static final Font FONT = Buffs.FONT;
	
	private final Sprite icon;
	private final Text text;
	
	public Shield() {
		icon = new Sprite(Images.SHIELD);
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		text = Nodes.text("", FONT);
		getChildren().addAll(icon, text);
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
		text.setText(String.valueOf(block));
		setVisible(true);
	}
	
}
