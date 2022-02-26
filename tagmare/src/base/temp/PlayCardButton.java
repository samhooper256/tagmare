package base.temp;

import base.VisualManager;
import javafx.scene.control.Button;

public class PlayCardButton extends Button {

	public PlayCardButton() {
		super("Play card");
		setOnAction(eh -> mouseClicked());
	}
	
	private void mouseClicked() {
		HandDisplay hd = GameScene.INSTANCE.handDisplay;
		HandCardRep selected = hd.selected();
		if(selected == null)
			return;
		VisualManager.requestPlayCardFromHand(selected.card());
	}
	
}
