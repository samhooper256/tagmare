package visuals;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import visuals.fxutils.*;

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
