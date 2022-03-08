package mechanics.cards;

public abstract class AbstractCard implements Card {

	private final CardTag tag;
	private final CardText text;
	
	protected AbstractCard(CardTag tag) {
		this.tag = tag;
		this.text = tag.text().copy();
	}
	
	@Override
	public final CardTag tag() {
		return tag;
	}

	@Override
	public String toString() {
		return String.format("%s (%d)", getClass().getSimpleName(), energyCost());
	}
	
	@Override
	public final CardText text() {
		return text;
	}
	
}
