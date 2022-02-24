package mechanics;

import base.Entity;
import mechanics.modifiers.ModifierSet;

public class Player implements Entity {

	private final ModifierSet modifiers;
	
	public Player() {
		modifiers = new ModifierSet();
	}
	
	public ModifierSet modifiers() {
		return modifiers;
	}
	
}
