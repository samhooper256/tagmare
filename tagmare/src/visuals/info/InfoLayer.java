package visuals.info;

import base.Updatable;
import javafx.scene.layout.Pane;
import visuals.fxutils.Nodes;

public class InfoLayer extends Pane implements Updatable {

	private final EndTurnButton endTurnButton;
	
	public InfoLayer() {
		endTurnButton = new EndTurnButton();
		Nodes.setLayout(endTurnButton, 100, 800);
		getChildren().add(endTurnButton);
	}
	
	@Override
	public void update(long diff) {
		endTurnButton.update(diff);
	}
	
	public EndTurnButton endTurnButton() {
		return endTurnButton;
	}
	
}
