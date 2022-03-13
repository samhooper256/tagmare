package visuals.calendar.topribbon;

import javafx.scene.control.Button;
import mechanics.Hub;
import visuals.Vis;

public class ViewDeck extends Button {

	public ViewDeck() {
		super("View Deck");
		setOnAction(ae -> action());
	}
	
	private void action() {
		Vis.deckGallery().startIntro(Hub.deck());
	}
	
}
