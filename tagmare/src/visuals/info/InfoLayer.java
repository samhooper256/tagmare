package visuals.info;

import javafx.scene.layout.Pane;
import visuals.fxutils.Nodes;

public class InfoLayer extends Pane {

	private final EndTurnButton endTurnButton;
	
	public InfoLayer() {
		endTurnButton = new EndTurnButton();
		Nodes.setLayout(endTurnButton, 100, 800);
		getChildren().add(endTurnButton);
	}
	
	public EndTurnButton endTurnButton() {
		return endTurnButton;
	}
	
}
