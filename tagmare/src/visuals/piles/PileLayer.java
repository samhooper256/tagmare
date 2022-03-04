package visuals.piles;

import javafx.scene.layout.Pane;

public class PileLayer extends Pane {

	private final DrawPileLayer draw;
	
	public PileLayer() {
		setPickOnBounds(false);
		this.draw = new DrawPileLayer();
		getChildren().add(draw);
	}
	
	public DrawPileLayer draw() {
		return draw;
	}
	
}
