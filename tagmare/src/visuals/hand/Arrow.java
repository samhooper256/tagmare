package visuals.hand;

import base.Updatable;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import visuals.Vis;

public class Arrow extends Group implements Updatable {

	private final Line line;
	
	public Arrow() {
		line = new Line(200, 800, 800, 200);
		getChildren().add(line);
	}
	
	@Override
	public void update(long diff) {
		line.setStartX(Vis.mouseX());
		line.setStartY(Vis.mouseY());
	}
	
}
