package mechanics.actions;

import mechanics.Hub;

/** When the player loses all their block at the start of their turn. */
public class SOTLoseBlock extends AbstractAction {

	public SOTLoseBlock() {
		super(null);
	}
	
	@Override
	public void execute() {
		Hub.playerBlock().set(0);
	}
	
}
