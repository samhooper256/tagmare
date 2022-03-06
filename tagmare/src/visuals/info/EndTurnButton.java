package visuals.info;

import base.*;
import javafx.scene.control.Button;
import mechanics.Hub;
import visuals.Fonts;

public class EndTurnButton extends Button implements Updatable {

	public EndTurnButton() {
		super("End Turn");
		setFont(Fonts.UI_18);
		setOnAction(ae -> mouseClicked());
	}
	
	private void mouseClicked() {
		if(canBePressed())
			Hub.combat().endPlayerTurn();
	}

	private boolean canBePressed() {
		return Hub.combat().canEndTurnExplicity() && !VisualManager.get().waitingOnAnimation();
	}

	@Override
	public void update(long diff) {
		setDisable(!canBePressed());
	}
	
}
