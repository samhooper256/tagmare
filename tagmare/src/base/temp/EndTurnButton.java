package base.temp;

import javafx.scene.control.Button;
import mechanics.Hub;

public class EndTurnButton extends Button {

	public EndTurnButton() {
		super("End Turn");
		setOnAction(eh -> mouseClicked());
	}
	
	private void mouseClicked() {
		if(Hub.combat().canEndTurn())
			Hub.combat().endPlayerTurn();
		else
			System.out.println("Can't end turn");
	}

	public void update() {
		setDisable(!Hub.combat().canEndTurn());
	}
	
}
