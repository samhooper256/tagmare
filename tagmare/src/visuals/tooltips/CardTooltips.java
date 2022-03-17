package visuals.tooltips;

import mechanics.cards.CardTag;

public final class CardTooltips {

	private CardTooltips() {
		
	}
	
	public static Tooltip createGuilt() {
		return Tooltip.titled(CardTag.GUILT.displayName(), "An unplayable and annoying card.");
	}
	
	public static Tooltip createOneTime() {
		return Tooltip.titled("One-Time", "This card can only be played once per combat.");
	}
	
}
