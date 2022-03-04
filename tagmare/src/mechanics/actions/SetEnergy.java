package mechanics.actions;

import mechanics.Hub;

public class SetEnergy extends AbstractAction {

	private final int destEnergy;
	
	public SetEnergy(int destEnergy) {
		super(null);
		this.destEnergy = destEnergy;
	}
	
	@Override
	public void execute() {
		Hub.energy().set(destEnergy);
	}

	public int destEnergy() {
		return destEnergy;
	}
	
}
