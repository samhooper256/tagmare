package utils;

import javafx.scene.Node;

public final class Nodes {

	private Nodes() {
		
	}
	
	/** x is set before y. */
	public static void setLayout(Node node, double x, double y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	
}
