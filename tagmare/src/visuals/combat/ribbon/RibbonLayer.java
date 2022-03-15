package visuals.combat.ribbon;

import javafx.scene.layout.Pane;

public class RibbonLayer extends Pane {

	private final CombatRibbon bottom;
	
	public RibbonLayer() {
		setMouseTransparent(true);
		setPickOnBounds(false);
		bottom = new CombatRibbon();
		getChildren().add(bottom);
	}
	
	public CombatRibbon ribbon() {
		return bottom;
	}
	
	public void debugPrint() {
		System.out.printf("RibbonLayer%n");
		bottom.debugPrint(1);
	}
}
