package mechanics.actions;

import mechanics.Hub;

public class ForcedEndTurn extends AbstractAction {

	public ForcedEndTurn() {
		super(null);
	}
	
	@Override
	public void execute() {
		Hub.combat().endPlayerTurnForcefully();
	}
	
}
