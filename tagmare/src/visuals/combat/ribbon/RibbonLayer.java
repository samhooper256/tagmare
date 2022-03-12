package visuals.combat.ribbon;

import javafx.scene.layout.Pane;

public class RibbonLayer extends Pane {

	private final BottomRibbon bottom;
	
	public RibbonLayer() {
		setMouseTransparent(true);
		setPickOnBounds(false);
		bottom = new BottomRibbon();
		getChildren().add(bottom);
	}
	
	public BottomRibbon bottom() {
		return bottom;
	}
	
}
