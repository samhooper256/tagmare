package visuals.combat.ribbon;

import java.util.HashMap;

import javafx.geometry.Side;
import mechanics.modifiers.ModifierTag;
import utils.Strings;
import visuals.combat.VerticalModifierIcon;
import visuals.tooltips.*;

public class PlayerModifierIcon extends VerticalModifierIcon {

	private static final HashMap<ModifierTag, PlayerModifierIcon> MAP = new HashMap<>();
	
	public static PlayerModifierIcon of(ModifierTag tag) {
		if(!MAP.containsKey(tag))
			MAP.put(tag, new PlayerModifierIcon(tag));
		return MAP.get(tag);
	}
	
	private final Tooltip tooltip;
	
	private PlayerModifierIcon(ModifierTag tag) {
		super(tag);
		TooltipManager ttm = new TooltipManager(this, Side.TOP);
		tooltip = new Tooltip(tag.displayName(), tag.description(0));
		ttm.column().add(tooltip);
		ttm.install();
	}

	@Override
	public void setInteger(int integer) {
		super.setInteger(integer);
		tooltip.setDescription(tag().description(integer));
	}
	
	public void debugPrint(int indent) {
		System.out.printf("%s%s%n", Strings.repeat("\t", indent), this);
	}
	
	@Override
	public String toString() {
		return String.format("PlayerModifierIcon[%s showing %s]", tag(), label().getText());
	}
	
}
