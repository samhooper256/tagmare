package visuals.combat;

import javafx.geometry.Side;
import mechanics.modifiers.ModifierTag;
import visuals.tooltips.*;

public class TippedModifierIcon extends AbstractModifierIcon {

	private final Tooltip tooltip;
	
	public TippedModifierIcon(ModifierTag tag, Side side) {
		super(tag);
		this.tooltip = Tooltip.titled(tag().displayName(), tag().description(0));
		TooltipManager ttm = new TooltipManager(this, side);
		ttm.column().add(tooltip);
		ttm.install();
	}
	
	@Override
	public void setInteger(int integer) {
		super.setInteger(integer);
		tooltip().setDescription(tag().description(integer));
	}
	
	public Tooltip tooltip() {
		return tooltip;
	}
	
}
