package visuals.combat.piles;

import javafx.scene.layout.Pane;

public class PileLayer extends Pane {

	private final DrawPileLayer draw;
	private final DiscardPileLayer discard;
	
	public PileLayer() {
		setPickOnBounds(false);
		draw = new DrawPileLayer();
		discard = new DiscardPileLayer();
		getChildren().addAll(draw, discard);
	}
	
	public DrawPileLayer draw() {
		return draw;
	}
	
	public DiscardPileLayer discard() {
		return discard;
	}
	
}
