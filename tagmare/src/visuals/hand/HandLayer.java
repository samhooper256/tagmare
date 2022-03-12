package visuals.hand;

import java.util.*;

import base.*;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import mechanics.cards.*;
import visuals.*;
import visuals.CardRepresentation.State;
import visuals.animations.*;
import visuals.piles.DiscardPileLayer;

/** Contains the {@link CardRepresentation CardRepresentations} for the player's {@link Hand}. */
public class HandLayer extends Pane implements Updatable {

	/** Each row is the x-coordinates for a given size. */
	public static final double[][] X_COORDS = new double[Hand.MAX_SIZE + 1][];
	
	private static final double SPACING = 12;
	private static final Duration
			CARD_DRAW_DURATION = Duration.millis(600),
			CARD_SHIFT_DURATION = CARD_DRAW_DURATION.multiply(2d / 3),
			FLY_TO_DISCARD_DURATION = Duration.millis(500);
	
	static {
		for(int count = 0; count <= Hand.MAX_SIZE; count++) {
			double[] coords = new double[count];
			double width = count * CardRepresentation.WIDTH + SPACING * (count - 1);
			for(int i = 0; i < count; i++)
				coords[i] = (GameScene.WIDTH * .5 - width * .5) + CardRepresentation.WIDTH * i + SPACING * i;
			X_COORDS[count] = coords;
		}
	}
	
	private class NaturalDiscardAnimation extends CardMoveAnimation {
		
		public NaturalDiscardAnimation(CardRepresentation cr) {
			super(cr, FLY_TO_DISCARD_DURATION);
			setDest(DiscardPileLayer.CARD_X, DiscardPileLayer.CARD_Y);
			setFinish(this::finisher);
		}
		
		private void finisher() {
			naturalDiscardFinisher(super.cardRepresentation());
		}
	}
	
	private class EOTDiscardAnimation extends CardMoveAnimation {
		
		public EOTDiscardAnimation(CardRepresentation cr) {
			super(cr, FLY_TO_DISCARD_DURATION);
			setStart();
			setDest(DiscardPileLayer.CARD_X, DiscardPileLayer.CARD_Y);
			setFinish(this::finisher);
		}
		
		private void finisher() {
			EOTDiscardFinisher(super.cardRepresentation());
		}
		
	}

	private final Group cardGroup, playGroup;
	private final Arrow arrow;
	
	private boolean addInProgress;
	private CardRepresentation selected;
	private Card cardBeingAdded;
	private Set<Card> cardsInPlay;
	
	public HandLayer() {
		setPickOnBounds(false);
		arrow = new Arrow();
		cardGroup = new Group();
		playGroup = new Group();
		cardsInPlay = new HashSet<>();
		getChildren().addAll(arrow, cardGroup, playGroup);
	}
	
	public void transferToPlayGroup(CardRepresentation cr) {
		if(!cardGroup.getChildren().contains(cr))
			throw new IllegalStateException(String.format("Not in cardGroup: %s", cr));
		playGroup.getChildren().add(cr); //this will remove cr from cardGroup.
	}
	@Override
	public void update(long diff) {
		arrow.update(diff);
		for(Node n : cardGroup.getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	public void startAddCardToRightAnimation(Card card) {
		addInProgress = true;
		cardBeingAdded = card;
		CardRepresentation cr = CardRepresentation.of(card);
		cr.setFaceUp();
		cardGroup.getChildren().add(cr);
		int count = cardCountForWidth();
		double[] coords = X_COORDS[count];
		Animation.manager().add(new CardMoveAnimation(cr, CARD_DRAW_DURATION).setStart()
				.setDest(coords[count - 1], CardRepresentation.Y).setFinish(this::addFinisher));
		for(int i = 0; i < count - 1; i++) {
			CardRepresentation irep = getRepresentation(i);
			if(cardsInPlay.contains(irep.card()))
				continue;
			Animation.manager().add(new CardMoveAnimation(irep, CARD_SHIFT_DURATION, Interpolator.SQRT)
					.setStart().setDest(coords[i], CardRepresentation.Y));
		}
	}
	
	private void addFinisher() {
		addInProgress = false;
		cardBeingAdded = null;
		VisualManager.get().checkedResumeFromAnimation();
	}
	
	/** Does not set {@link #selected()} to {@code null} since that has already been done by
	 * {@link #moveSelectedToInPlay(Card)}. */
	public void startNaturalDiscard(Card card) {
		if(!cardsInPlay.contains(card))
			throw new IllegalArgumentException(String.format("Not in play: %s", card));
		CardRepresentation cr = CardRepresentation.of(card);
		Animation.manager().add(new NaturalDiscardAnimation(cr).setStart());
		startReorganize();
		cr.startExpandBackToNormalSize();
	}
	
	private void naturalDiscardFinisher(CardRepresentation cr) {
		Vis.handLayer().removeFromInPlayOrThrow(cr.card());
		Vis.pileLayer().discard().addToTop(cr); //this removes cr as a child of cardGroup.
		VisualManager.get().checkedResumeFromAnimation();
	}
	
	public void startEOTToDiscard(Card card) {
		CardRepresentation cr = CardRepresentation.of(card);
		cr.cancelAnimation();
		Animation.manager().add(new EOTDiscardAnimation(cr));
		cr.setState(State.FLYING_TO_DISCARD);
	}
	
	private void EOTDiscardFinisher(CardRepresentation cr) {
		Vis.pileLayer().discard().addToTop(cr);
		cr.setState(State.DOWN);
		Vis.manager().checkedResumeFromAnimation();
	}
	
	public void startReorganize() {
		int count = cardCountForWidth();
		int countExcludingPlaying = count;
		for(int i = 0; i < count; i++)
			if(cardsInPlay.contains(getRepresentation(i).card()))
				countExcludingPlaying--;
		double[] coords = X_COORDS[countExcludingPlaying];
		int ci = 0;
		for(int i = 0; i < count; i++) {
			CardRepresentation cr = getRepresentation(i);
			if(cardsInPlay.contains(cr.card()))
				continue;
			Animation.manager().add(new CardMoveAnimation(cr, CARD_SHIFT_DURATION,
					Interpolator.SQRT).setStart().setDest(coords[ci], CardRepresentation.Y));
			ci++;
		}
	}
	
	public void notifyClickedInDeadSpace() {
		if(hasSelected() && selected().state() == State.TO_POISED)
			selected().cancelPoise(); //sets selected to null
	}
	
	public void notifyTurnEnded() {
		setSelected(null);
		arrow.unbindAndHide();
	}
	
	private int cardCountForWidth() {
		return cardGroup.getChildren().size();
	}
	
	/** Removes the {@link CardRepresentation} as descendant node of this {@link HandLayer} only. */
	public void removeOrThrow(CardRepresentation cr) {
		if(!cardGroup.getChildren().remove(cr))
			throw new IllegalArgumentException(String.format("Not in this HandLayer: %s.\nchildren=%s", cr,
					getChildren()));
	}
	
	public void moveSelectedToInPlay(Card card) {
		if(card == null || selected() == null || card != selected().card())
			throw new IllegalArgumentException(
					String.format("card is not the selected one (card=%s, selected=%s)", card, selected()));
		setSelected(null);
		cardsInPlay.add(card);
	}
	
	public void removeFromInPlayOrThrow(Card card) {
		if(!cardsInPlay.remove(card))
			throw new IllegalArgumentException(String.format("Not in play: %s", card));
	}
	
	private CardRepresentation getRepresentation(int index) {
		return (CardRepresentation) cardGroup.getChildren().get(index);
	}
	
	public double xCoord(CardRepresentation cr) {
		int index = cardIndex(cr);
		return X_COORDS[cardCountForWidth()][index];
	}
	
	public int cardIndex(CardRepresentation cr) {
		return cardGroup.getChildren().indexOf(cr);
	}
	
	public boolean addInProgress() {
		return addInProgress;
	}
	
	public Card cardBeingAdded() {
		return cardBeingAdded;
	}
	
	public void setSelected(CardRepresentation cr) {
		if(cr != null && !cardGroup.getChildren().contains(cr))
			throw new IllegalArgumentException(String.format("Not in the HandLayer: %s", cr));
		System.out.printf("set selected: %s%n", cr);
		selected = cr;
	}
	
	public boolean hasSelected() {
		return selected != null;
	}
	
	public CardRepresentation selected() {
		return selected;
	}
	
	public Arrow arrow() {
		return arrow;
	}
	
	/** All {@link Group#getChildren() children} are {@link CardRepresentation CardRepresentations}. */
	public Group cardGroup() {
		return cardGroup;
	}
	
	public void debugPrint() {
		System.out.printf("HandLayer // selected=%s, cardBeingAdded=%s%n", selected, cardBeingAdded);
		ObservableList<Node> cgc = cardGroup.getChildren();
		System.out.printf("cardGroup.getChildren() (%d):%n", cgc.size());
		System.out.println();
		for(Node n : cardGroup.getChildren()) {
			((CardRepresentation) n).debugPrint("\t");
			System.out.println();
		}
		
	}
}
