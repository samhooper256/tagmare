package mechanics.modifiers.buffs;

import mechanics.modifiers.*;

public class PlanningAhead extends IntegerModifier implements VisibleBuff {

	public PlanningAhead(int integer) {
		super(integer);
	}

	@Override
	public ModifierTag tag() {
		return ModifierTag.PLANNING_AHEAD;
	}

}
