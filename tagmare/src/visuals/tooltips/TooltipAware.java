package visuals.tooltips;

import javafx.scene.Node;

/** For a {@link Node} that has a {@link TooltipManager} <em>and</em> can provide a reference to that
 * {@link #tooltipManager()}. */
public interface TooltipAware {

	TooltipManager tooltipManager();
	
}
