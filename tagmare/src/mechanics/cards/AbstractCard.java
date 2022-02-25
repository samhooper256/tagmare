package mechanics.cards;

public abstract class AbstractCard implements Card {

	private final CardTag tag;
	
	protected AbstractCard(CardTag tag) {
		this.tag = tag;
	}
	
	@Override
	public CardTag tag() {
		return tag;
	}
	
}
