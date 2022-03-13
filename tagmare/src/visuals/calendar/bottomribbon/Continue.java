package visuals.calendar.bottomribbon;

import javafx.scene.control.Button;
import visuals.Vis;

final class Continue extends Button {

	public Continue() {
		super("Continue");
		setOnMouseClicked(me -> mouseClicked());
	}
	
	public void mouseClicked() {
		setDisable(true);
		Vis.calendarEye().startTransition();
	}
	
}
