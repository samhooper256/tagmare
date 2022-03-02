package visuals.hand;

import javafx.util.Duration;
import visuals.CardRepresentation;
import visuals.animations.*;

public class CardMoveAnimation extends AbstractAnimation {

	private final CardRepresentation cardRepresentation;
	
	private double destX, destY, startX, startY;
	
	public CardMoveAnimation(CardRepresentation cardRepresentation, Duration duration) {
		this(cardRepresentation, duration, Interpolator.bow(.1));
	}
	
	public CardMoveAnimation(CardRepresentation cardRepresentation, Duration duration, Interpolator interpolator) {
		super(duration, interpolator);
		this.cardRepresentation = cardRepresentation;
	}
	
	/** Returns {@code this}. */
	public CardMoveAnimation setDest(double destX, double destY) {
		this.destX = destX;
		this.destY = destY;
		return this;
	}
	
	
	/** Returns {@code this}. */
	public CardMoveAnimation setStart(double startX, double startY) {
		this.startX = startX;
		this.startY = startY;
		return this;
	}
	
	/** {@link #setDest(double, double) Sets the start} to the current location of the {@link #cardRepresentation()}.
	 * Returns {@code this}. */
	public CardMoveAnimation setStart() {
		return setStart(cardRepresentation().getLayoutX(), cardRepresentation().getLayoutY());
	}

	@Override
	public void interpolate(double frac) {
		cardRepresentation.setLayoutX(startX + (destX - startX) * frac);
		cardRepresentation.setLayoutY(startY + (destY - startY) * frac);
	}
	
	public CardRepresentation cardRepresentation() {
		return cardRepresentation;
	}
	
}