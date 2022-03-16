package mechanics.cards.passives;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.Shortcuts;

public class KeyboardShortcuts extends AbstractCard implements Passive {

	public static final int DAMAGE = 1;
	
	public KeyboardShortcuts() {
		super(CardTag.KEYBOARD_SHORTCUTS);
	}

	@Override
	public Card copy() {
		return new KeyboardShortcuts();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return Action.list(ApplyModifier.toPlayer(new Shortcuts(DAMAGE), this));
	}
	
}
