package mechanics.actions;

import mechanics.*;

/** The energy change resulting from this {@link Action} must not make the {@link Energy} go negative; otherwise, an
 * exception will be thrown. */
public class ChangeEnergy extends AbstractAction {

	private int amount;
	
	public ChangeEnergy(int amount, ActionSource source) {
		super(source);
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		Hub.combat().energy().increase(amount());
	}

	public int amount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
