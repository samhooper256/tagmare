package visuals.fxutils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.*;

public final class Nodes {

	private Nodes() {
		
	}
	
	public static void setMaxSize(Region r, double width, double height) {
		r.setMaxWidth(width);
		r.setMaxHeight(height);
	}
	
	public static void setMinSize(Region r, double width, double height) {
		r.setMinWidth(width);
		r.setMinHeight(height);
	}
	
	public static void setPrefSize(Region r, double width, double height) {
		r.setPrefWidth(width);
		r.setPrefHeight(height);
	}
	
	public static void setPrefSize(Region r, double size) {
		setPrefSize(r, size, size);
	}
	
	public static void setPrefAndMaxSize(Region r, double width, double height) {
		setPrefSize(r, width, height);
		setMaxSize(r, width, height);
	}
	
	public static void setPrefAndMaxWidth(Region r, double width) {
		r.setPrefWidth(width);
		r.setMaxWidth(width);
	}
	
	public static void setPrefAndMaxHeight(Region r, double height) {
		r.setPrefHeight(height);
		r.setMaxHeight(height);
	}
	
	public static void setLayout(Node node, double x, double y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	
	public static void setScale(Region r, double factor) {
		setScale(r, factor, factor);
	}

	public static void setScale(Region r, double x, double y) {
		r.setScaleX(x);
		r.setScaleY(y);
	}
	
	public static void setStart(Line line, double x, double y) {
		line.setStartX(x);
		line.setStartY(y);
	}
	
	public static void setEnd(Line line, double x, double y) {
		line.setEndX(x);
		line.setEndY(y);
	}
	
	public static double centerXMax(Region r) {
		return r.getLayoutX() + r.getMaxWidth() * .5;
	}
	
	public static double centerYMax(Region r) {
		return r.getLayoutY() + r.getMaxHeight() * .5;
	}
	
	public static Label label(Font font) {
		return label("", font);
	}
	
	public static Label label(String text, Font font) {
		Label l = new Label(text);
		l.setFont(font);
		return l;
	}
	
	public static Label label(Font font, Paint textFill) {
		return label("", font, textFill);
	}
	
	public static Label label(String text, Font font, Paint textFill) {
		Label l = new Label(text);
		l.setFont(font);
		l.setTextFill(textFill);
		return l;
	}
	
	public static Text text(String text, Font font) {
		Text l = new Text(text);
		l.setFont(font);
		return l;
	}
	
}
