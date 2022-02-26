package mechanics.actions;

import mechanics.Hub;

public class SetEnergy extends AbstractAction {

	private final int amount;
	
	public SetEnergy(int amount) {
		super(null);
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		Hub.energy().set(amount);
	}

	public int amount() {
		return amount;
	}
	
}
