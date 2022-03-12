package visuals.combat.inquiry;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import mechanics.Hub;
import mechanics.cards.Card;
import mechanics.input.*;
import utils.Colls;
import visuals.*;
import visuals.animations.*;
import visuals.combat.hand.HandLayer;
import visuals.fxutils.*;

/** Invisible by default. */
public class InquiryLayer extends Pane {

	public static final double
		PHANTOM_Y = 350,
		CONFIRM_Y = 650, CONFIRM_WIDTH = 80, CONFIRM_HEIGHT = 30;

	private static final Duration FADE_DURATION = Duration.millis(500);
	
	private class FadeIn extends FadeAnimation {
		
		public FadeIn() {
			super(InquiryLayer.this, FADE_DURATION, 0, 1);
			setFinish(() -> setMouseTransparent(false));
		}
		
	}
	
	private class FadeOut extends FadeAnimation {
		
		private final List<Card> cardsToSend;
		
		public FadeOut(List<Card> cardsToSend) {
			super(InquiryLayer.this, FADE_DURATION, 1, 0);
			this.cardsToSend = cardsToSend;
			setFinish(this::finish);
		}
		
		private void finish() {
			phantomGroup.getChildren().clear();
			setVisible(false);
			inquiry = null;
			Hub.combat().requestSupplyCardsToInquiry(cardsToSend);
		}
		
	}
	
	private final Instructions instructions;
	private final Button confirm;
	private final Group phantomGroup;
	
	private CardInquiry inquiry;
	
	public InquiryLayer() {
		confirm = new Button("Confirm");
		confirm.setOnAction(ae -> confirmAction());
		instructions = new Instructions();
		phantomGroup = new Group();
		Nodes.setPrefAndMaxSize(this, GameScene.WIDTH, GameScene.HEIGHT);
		Nodes.setLayout(confirm, GameScene.CENTER_X - CONFIRM_WIDTH * .5, CONFIRM_Y);
		Nodes.setPrefAndMaxSize(confirm, CONFIRM_WIDTH, CONFIRM_HEIGHT);
		setBackground(Backgrounds.of(Color.grayRgb(0, 0.5)));
		getChildren().addAll(confirm, phantomGroup, instructions);
		setOpacity(0);
		setVisible(false);
		setMouseTransparent(true);
	}

	//Assumes opacity is 0.
	public void startInquiry(CardInquiry inquiry) {
		this.inquiry = inquiry;
		instructions.setText(inquiry.displayText());
		CardRepresentation.of(Colls.any(Hub.combat().cardsInPlay())).startFlyToTop();
		setVisible(true);
		Animation.manager().add(new FadeIn());
	}
	
	public void clickedCardFromHand(Card card) {
		ObservableList<Node> c = phantomGroup.getChildren();
		if(inquiry.selection() instanceof RangeSelection && c.size() == ((RangeSelection) inquiry.selection()).max())
			return;
		PhantomCard pc = PhantomCard.of(card);
		if(!c.contains(pc)) {
			double[] coords = HandLayer.X_COORDS[c.size() + 1];
			pc.setLayoutY(PHANTOM_Y);
			pc.updateText();
			for(int i = 0; i < c.size(); i++)
				c.get(i).setLayoutX(coords[i]);
			pc.setLayoutX(coords[coords.length - 1]);
			c.add(pc);
		}
	}
	
	public void clickedPhantom(Card card) {
		if(isMouseTransparent())
			return;
		ObservableList<Node> c = phantomGroup.getChildren();
		PhantomCard pc = PhantomCard.of(card);
		if(c.contains(pc)) {
			c.remove(pc);
			double[] coords = HandLayer.X_COORDS[c.size()];
			for(int i = 0; i < c.size(); i++)
				c.get(i).setLayoutX(coords[i]);
		}
	}
	
	public Group phantomGroup() {
		return phantomGroup;
	}
	
	/** Modifiable (changes do not affect this). Returns the {@link Card Cards} that are shown as phantoms. */
	public List<Card> cards() {
		ObservableList<Node> c = phantomGroup.getChildren();
		List<Card> cards = new ArrayList<>(c.size());
		for(Node n : c)
			cards.add(((PhantomCard) n).card());
		return cards;
	}
	
	private void confirmAction() {
		List<Card> cards = cards();
		if(Hub.combat().canSupplyCardsToInquiry(cards)) {
			setMouseTransparent(true);
			Animation.manager().add(new FadeOut(cards));
		}
	}
	
	public boolean isActive() {
		return inquiry != null;
	}
	
}
