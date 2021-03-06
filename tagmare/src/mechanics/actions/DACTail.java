package mechanics.actions;

import mechanics.Hub;
import mechanics.actions.list.ActionListBuilder;
import mechanics.cards.attacks.DivideAndConquer;
import mechanics.effects.*;
import utils.RNG;

/** All actions produced by this one ({@link DealDamage} and {@link DACTail}) have the original
 * {@link DivideAndConquer} card as their source. This is needed for {@link DealDamageEffects} to work correctly. */
public class DACTail extends AbstractAction {

	private final int remaining;
	
	public DACTail(int remaining, DivideAndConquer source) {
		super(source);
		if(remaining <= 0)
			throw new IllegalArgumentException(String.format("remaining=%d", remaining));
		this.remaining = remaining;
	}

	@Override
	public void execute() {
		ActionListBuilder list = Action.listBuilder();
		list.add(AttackEffects.applyToSingle(
			source(), new DealDamage(DivideAndConquer.DAMAGE, source(), RNG.pickOrThrow(Hub.enemies()))));
		if(remaining > 1)
			list.add(new DACTail(remaining - 1, source()));
		Hub.stack().pushReversed(list.build());
	}
	
	@Override
	public DivideAndConquer source() {
		return (DivideAndConquer) super.source();
	}
	
	@Override
	public String toString() {
		return String.format("DACTail[remaining=%d]", remaining);
	}
	
}
