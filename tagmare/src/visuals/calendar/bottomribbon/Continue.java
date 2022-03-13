package visuals.calendar.bottomribbon;

import javafx.scene.control.Button;
import visuals.Vis;

public final class Continue extends Button {

	public Continue() {
		super("Continue");
		setOnMouseClicked(me -> mouseClicked());
	}
	
	public void mouseClicked() {
		setDisable(true);
		Vis.calendarEye().startTransition();
	}
	
	/** @throws IllegalStateException if not {@link #isDisable()}. */
	public void reEnable() {
		if(!isDisable())
			throw new IllegalStateException("Not disabled");
		setDisable(false);
	}
	
}
