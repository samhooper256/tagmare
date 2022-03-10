package mechanics.actions;

import mechanics.*;
import mechanics.modifiers.buffs.Reserves;

/** Spawns two actions: set the player's energy to 0, then adding Reserves modifier. */
public class ReserveEnergy extends AbstractAction {

	public ReserveEnergy(mechanics.cards.skills.Reserves source) {
		super(source);
	}

	@Override
	public void execute() {
		Energy e = Hub.energy();
		int amount = e.amount();
		Hub.stack().pushReversed(
			new SetEnergy(0),
			ApplyModifier.toPlayer(new Reserves(amount + mechanics.cards.skills.Reserves.ADDITIONAL), this)
		);
	}

}
