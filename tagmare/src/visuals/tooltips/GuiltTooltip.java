package visuals.tooltips;

import mechanics.cards.CardTag;

public class GuiltTooltip extends Tooltip {

	public GuiltTooltip() {
		super(CardTag.GUILT.displayName(), "An unplayable and annoying card.");
	}
	
}
