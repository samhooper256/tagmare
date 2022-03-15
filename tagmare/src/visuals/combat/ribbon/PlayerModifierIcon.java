package visuals.combat.ribbon;

import java.util.HashMap;

import mechanics.modifiers.ModifierTag;
import visuals.AbstractModifierIcon;

public class PlayerModifierIcon extends AbstractModifierIcon {

	private static final HashMap<ModifierTag, PlayerModifierIcon> MAP = new HashMap<>();
	
	public static PlayerModifierIcon of(ModifierTag tag) {
		if(!MAP.containsKey(tag))
			MAP.put(tag, new PlayerModifierIcon(tag));
		return MAP.get(tag);
	}
	
	private PlayerModifierIcon(ModifierTag tag) {
		super(tag);
	}

}
