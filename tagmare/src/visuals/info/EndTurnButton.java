package visuals.info;

import base.VisualManager;
import javafx.scene.control.Button;
import mechanics.Hub;

public class EndTurnButton extends Button {

	public EndTurnButton() {
		super("End Turn");
		setOnAction(ae -> mouseClicked());
	}
	
	private void mouseClicked() {
		System.out.printf("mouseclicked%n");
		if(canBePressed()) {
			System.out.printf("\tthrough%n");
			Hub.combat().endPlayerTurn();
		}
	}
	
	private boolean canBePressed() {
		return Hub.combat().canEndTurnExplicity() && !VisualManager.get().waitingOnAnimation();
	}
	
}
