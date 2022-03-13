package visuals.eye;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class TopEyelid extends Path {

	public static final double CLOSED_ARC_Y = .001, OPEN_ARC_Y = 765;
	
	private final ArcTo arc;
	
	public TopEyelid() {
		MoveTo mt = new MoveTo(Eye.LEFT_X, Eye.Y);
		arc = new ArcTo(Eye.RADIUS_X, OPEN_ARC_Y, 0, Eye.RIGHT_X, Eye.Y, false, true);
		VLineTo v1 = new VLineTo(0);
		HLineTo h = new HLineTo(Eye.LEFT_X);
		VLineTo v2 = new VLineTo(Eye.Y);
		
		setFill(Color.BLACK);
		getElements().addAll(mt, arc, v1, h, v2, new ClosePath());
	}
	
	public DoubleProperty arcYProperty() {
		return arc.radiusYProperty();
	}
	
	public void setOpen() {
		arcYProperty().set(OPEN_ARC_Y);
	}
	
	public void setClosed() {
		arcYProperty().set(CLOSED_ARC_Y);
	}
	
}
