package visuals.piles;

import javafx.scene.layout.Pane;

public class PileLayer extends Pane {

	private final DrawPileLayer draw;
	
	public PileLayer() {
		this.draw = new DrawPileLayer();
	}
	
	public DrawPileLayer draw() {
		return draw;
	}
	
}
