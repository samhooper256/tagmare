package mechanics.actions;

import mechanics.Hub;

public class SpendEnergy extends AbstractAction {

	private final int amount;
	
	public SpendEnergy(int amount, ActionSource source) {
		super(source);
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		Hub.energy().decrease(spentEnergy());
	}

	public int spentEnergy() {
		return amount;
	}
	
}
