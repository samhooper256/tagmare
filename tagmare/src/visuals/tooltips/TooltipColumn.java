package visuals.tooltips;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/** <p>{@link Tooltip Tooltips} shown in the order added. Do not modify {@link #getChildren()};
 * instead, use {@link #add(Tooltip)}, {@link #addAll(List)}, {@link #remove(Tooltip)}, and
 * {@link #removeOrThrow(Tooltip)}.</p>
 * <p>The {@link #getWidth()} and {@link #getHeight()} of the column account for (include) any padding/spacing around
 * the outsides of the tooltips.</p>
 * <p>{@link #setVisible(boolean) Invisible} by defualt.</p>*/
public class TooltipColumn extends VBox {

	public static final double SPACING = 2;
	
	public TooltipColumn() {
		super(SPACING);
		setVisible(false);
	}
	
	public void add(Tooltip tooltip) {
		getChildren().add(tooltip);
	}

	public void addAll(List<? extends Tooltip> tooltips) {
		getChildren().addAll(tooltips);
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
	
	public void update() {
		for(Node n : getChildren())
			((Tooltip) n).update();
	}
	
}
