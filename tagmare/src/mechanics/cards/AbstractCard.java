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

	@Override
	public String toString() {
		return String.format("%s (%d)", getClass().getSimpleName(), energyCost());
	}
	
}
