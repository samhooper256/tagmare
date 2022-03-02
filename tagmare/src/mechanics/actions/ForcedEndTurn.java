package mechanics.actions;

import mechanics.Hub;

public class ForcedEndTurn extends AbstractAction {

	public ForcedEndTurn(ActionSource source) {
		super(source);
	}
	
	@Override
	public void execute() {
		Hub.combat().endPlayerTurnForcefully();
	}
	
}
