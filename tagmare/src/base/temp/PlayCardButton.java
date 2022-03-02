package base.temp;

import javafx.scene.control.Button;
import mechanics.cards.Card;
import visuals.VisualManager;

public class PlayCardButton extends Button {

	public PlayCardButton() {
		super("Play card");
		setOnAction(eh -> mouseClicked());
	}
	
	private void mouseClicked() {
		HandDisplay hd = TempScene.INSTANCE.handDisplay;
		HandCardRep selected = hd.selected();
		if(selected == null)
			return;
		Card card = selected.card();
		if(card.isTargetted())
			return;
		VisualManager.requestPlayCardFromHand(card);
	}
	
}
