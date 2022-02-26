package base.temp;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ButtonBar extends HBox {

	private final PlayCardButton playCardButton;
	private final EndTurnButton endTurnButton;
	
	public ButtonBar() {
		this.playCardButton = new PlayCardButton();
		this.endTurnButton = new EndTurnButton();
		setAlignment(Pos.CENTER);
		getChildren().addAll(playCardButton, endTurnButton);
	}
	
}
