package visuals;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import visuals.fxutils.*;

/** A {@link StackPane} with one child node: a {@link ResizableImage}. The size of this {@link Sprite} can be controlled
 * via {@link #setSize(double, double)}. The size can be retrieved the {@link #getPrefWidth()} and
 * {@link #getPrefHeight()}. Clients are welcome to add other nodes to this {@code StackPane} on top of the
 * image; but clients must not remove the image or relocate it from the bottom position in this {@code StackPane}
 * (this means the {@link #getChildren() children} should never be cleared). */
public class Sprite extends StackPane {

	private final ResizableImage resizableImage;
	
	public Sprite(Image image) {
		this.resizableImage = new ResizableImage(image);
		setSize(image.getWidth(), image.getHeight());
		getChildren().add(resizableImage);
	}
	
	public void setSize(double width, double height) {
		Nodes.setPrefAndMaxSize(this, width, height);
	}
	
	public void setImage(Image image) {
		resizableImage.setImage(image);
	}
	
}
