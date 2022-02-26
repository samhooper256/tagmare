package base.temp;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ButtonBar extends HBox {

	public final PlayCardButton playCardButton;
	public final EndTurnButton endTurnButton;
	
	public ButtonBar() {
		this.playCardButton = new PlayCardButton();
		this.endTurnButton = new EndTurnButton();
		setAlignment(Pos.CENTER);
		getChildren().addAll(playCardButton, endTurnButton);
	}

	public void update() {
		endTurnButton.update();
	}
	
}
