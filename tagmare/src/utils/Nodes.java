package utils;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public final class Nodes {

	private Nodes() {
		
	}
	
	/** x is set before y. */
	public static void setLayout(Node node, double x, double y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	
	/** width is set before height. */
	public static void setMaxSize(Region region, double width, double height) {
		region.setMaxWidth(width);
		region.setMaxHeight(height);
	}
	
}
