package mechanics.actions;

import mechanics.*;
import mechanics.combat.Combat;
import mechanics.enemies.Enemy;

/** {@link Action} to be executed when the player kills all the enemies in the {@link Combat}.
 * {@link #canExecute() Can't execute} if there are still {@link Enemy Enemies} alive in the {@link Combat}. */
public class WinCombat extends AbstractAction {

	/** {@code null} source. */
	public WinCombat() {
		super(null);
	}

	@Override
	public void execute() {
		Hub.combat().winCombatAction();
	}
	
	@Override
	public boolean canExecute() {
		return super.canExecute() && Hub.combat().enemies().size() == 0;
	}
	
}
