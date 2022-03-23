package visuals.combat.ribbon;

import java.util.HashMap;

import mechanics.modifiers.ModifierTag;
import utils.Strings;
import visuals.combat.VerticalModifierIcon;

public class PlayerModifierIcon extends VerticalModifierIcon {

	private static final HashMap<ModifierTag, PlayerModifierIcon> MAP = new HashMap<>();
	
	public static PlayerModifierIcon of(ModifierTag tag) {
		if(!MAP.containsKey(tag))
			MAP.put(tag, new PlayerModifierIcon(tag));
		return MAP.get(tag);
	}
	
	private PlayerModifierIcon(ModifierTag tag) {
		super(tag);
	}
	
	public void debugPrint(int indent) {
		System.out.printf("%s%s%n", Strings.repeat("\t", indent), this);
	}
	
	@Override
	public String toString() {
		return String.format("PlayerModifierIcon[%s showing %s]", tag(), label().getText());
	}
	
}
