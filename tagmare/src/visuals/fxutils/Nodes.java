package visuals.fxutils;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public final class Nodes {

	private Nodes() {
		
	}
	
	public static void setMaxSize(Region r, double width, double height) {
		r.setMaxWidth(width);
		r.setMaxHeight(height);
	}
	
	public static void setPrefSize(Region r, double width, double height) {
		r.setPrefWidth(width);
		r.setPrefHeight(height);
	}
	
	public static void setPrefAndMaxSize(Region r, double width, double height) {
		setPrefSize(r, width, height);
		setMaxSize(r, width, height);
	}
	
	public static void setLayout(Node node, double x, double y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	
}
