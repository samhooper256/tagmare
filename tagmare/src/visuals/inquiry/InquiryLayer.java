package visuals.inquiry;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mechanics.Hub;
import mechanics.input.CardInquiry;
import utils.Colls;
import visuals.*;
import visuals.fxutils.*;

/** Invisible by default. */
public class InquiryLayer extends Pane {

	public static final double CONFIRM_WIDTH = 80, CONFIRM_HEIGHT = 30, CONFIRM_Y = 650;
	
	private final Button confirm;
	
	private CardInquiry inquiry;
	
	public InquiryLayer() {
		confirm = new Button();
		Nodes.setPrefAndMaxSize(this, GameScene.WIDTH, GameScene.HEIGHT);
		Nodes.setLayout(confirm, GameScene.CENTER_X - CONFIRM_WIDTH * .5, CONFIRM_Y);
		setBackground(Backgrounds.of(Color.grayRgb(120, 0.5)));
		getChildren().add(confirm);
		setOpacity(0);
		setVisible(false);
	}
	
	public void startInquiry(CardInquiry inquiry) {
		this.inquiry = inquiry;
		CardRepresentation.of(Colls.any(Hub.combat().cardsInPlay())).startFlyToTop();
		setOpacity(1);
		setVisible(true);
	}
	
}
