package mechanics.actions;

import mechanics.*;

/** The energy change resulting from this {@link Action} will go to {@code 0} instead of going negative. */
public class ChangeEnergy extends AbstractAction {

	private int amount;
	
	public ChangeEnergy(int amount, ActionSource source) {
		super(source);
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		Energy e = Hub.combat().energy();
		e.increase(Math.max(-e.amount(), amount()));
	}

	public int amount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
