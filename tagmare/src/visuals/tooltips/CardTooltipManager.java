package visuals.tooltips;

import java.util.*;

import javafx.geometry.Side;
import mechanics.cards.*;
import mechanics.modifiers.ModifierTag;
import visuals.*;

public class CardTooltipManager extends TooltipManager {

	public static CardTooltipManager install(AbstractCardRepresentation cardRepresentation) {
		CardTooltipManager ctm = new CardTooltipManager(cardRepresentation);
		ctm.install();
		return ctm;
	}
	
	public CardTooltipManager(AbstractCardRepresentation cardRepresentation) {
		super(cardRepresentation, Side.RIGHT);
		column().addAll(generateTooltips());
	}
	
	private List<Tooltip> generateTooltips() {
		Card card = region().card();
		String text = card.text().defaultText();
		List<Tooltip> list = new ArrayList<>();
		//First, if it's OT, generate that:
		if(card.isOneTime())
			list.add(CardTooltips.createOneTime());
		//Scan the card's text for modifiers:
		Set<String> seen = new HashSet<>();
		outer:
		for(int i = 0; i < text.length(); i++) {
			//Test for the display name of a modifier starting at index i:
			for(ModifierTag tag : ModifierTag.values()) {
				String dn = tag.displayName();
				if(seen.contains(dn))
					continue;
				if(text.regionMatches(i, dn, 0, dn.length())) {
					list.add(tooltipFor(tag));
					seen.add(dn);
					continue outer;
				}
			}
		}
		//Check for the Guilt card separately:
		String guilt = CardTag.GUILT.displayName();
		if(text.startsWith(guilt) || text.endsWith(guilt) || text.contains(" " + guilt + " "))
			list.add(CardTooltips.createGuilt());
		return list;
	}
	
	private static Tooltip tooltipFor(ModifierTag tag) {
		return Tooltip.titled(tag.displayName(), tag.generalDescription());
	}
	
	@Override
	public AbstractCardRepresentation region() {
		return (AbstractCardRepresentation) super.region();
	}
	
}
