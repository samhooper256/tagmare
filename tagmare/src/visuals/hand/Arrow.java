package visuals.hand;

import base.Updatable;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import visuals.*;
import visuals.fxutils.Nodes;

public class Arrow extends Group implements Updatable {

	private final Line line;
	
	private CardRepresentation cardRepresentation;
	
	public Arrow() {
		line = new Line(200, 800, 800, 200);
		setVisible(false);
		setPickOnBounds(false);
		setMouseTransparent(true);
		cardRepresentation = null;
		getChildren().add(line);
	}
	
	@Override
	public void update(long diff) {
		if(cardRepresentation != null) {
			Nodes.setStart(line, Nodes.centerXMax(cardRepresentation), Nodes.centerYMax(cardRepresentation));
			Nodes.setEnd(line, Vis.mouseX(), Vis.mouseY());
		}
	}
	
	public void unbindAndHide() {
		setCardRepresentation(null);
		setVisible(false);
	}
	
	public void displayFor(CardRepresentation cardRepresentation) {
		setCardRepresentation(cardRepresentation);
		showOrThrow();
	}
	
	public void setCardRepresentation(CardRepresentation cardRepresentation) {
		this.cardRepresentation = cardRepresentation;
	}
	
	/** @throws IllegalArgumentException if {@link #cardRepresentation()} is {@code null}. */
	public void showOrThrow() {
		if(cardRepresentation == null)
			throw new IllegalStateException("CardRepresentation is null");
		setVisible(true);
	}
	
	public CardRepresentation cardRepresentation() {
		return cardRepresentation;
	}
	
}