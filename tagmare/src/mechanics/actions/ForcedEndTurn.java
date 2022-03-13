package mechanics.actions;

import mechanics.*;
import mechanics.combat.CombatState;

/** {@link #canExecute() Can't execute} if it is not the player's turn. */
public class ForcedEndTurn extends AbstractAction {

	public ForcedEndTurn(ActionSource source) {
		super(source);
	}
	
	@Override
	public void execute() {
		Hub.combat().endPlayerTurnForcefully();
	}
	
	@Override
	public boolean canExecute() {
		return super.canExecute() && Hub.combat().state() == CombatState.PLAYER_TURN; 
	}
	
}
