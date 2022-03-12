package visuals.combat.debug;

import javafx.scene.layout.Pane;
import visuals.GameScene;
import visuals.fxutils.Nodes;

public class DebugLayer extends Pane {

	private final StackDisplay stackDisplay;
	
	public DebugLayer() {
		setMouseTransparent(true);
		stackDisplay = new StackDisplay();
		Nodes.setLayout(stackDisplay, GameScene.CENTER_X, 0);
		getChildren().add(stackDisplay);
	}
	
	public StackDisplay stackDisplay() {
		return stackDisplay;
	}
	
}
