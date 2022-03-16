package visuals.tooltips;

import javafx.scene.layout.VBox;

/** {@link Tooltip Tooltips} shown in the order added. Do not modify {@link #getChildren()};
 * instead, use {@link #add(Tooltip)}, {@link #remove(Tooltip)}, and {@link #removeOrThrow(Tooltip)}. */
public class TooltipColumn extends VBox {

	public TooltipColumn() {
		
	}
	
	public void add(Tooltip tooltip) {
		getChildren().add(tooltip);
	}

	/** {@code false} iff not present. */
	public boolean remove(Tooltip tooltip) {
		return getChildren().remove(tooltip);
	}
	
	public void removeOrThrow(Tooltip tooltip) {
		if(!remove(tooltip))
			throw new IllegalArgumentException(String.format("Not present: %s", tooltip));
	}
	
	/** {@code true} iff this {@link TooltipColumn} has exactly one {@link Tooltip}. */
	public boolean isSingle() {
		return getChildren().size() == 1;
	}
	
}
