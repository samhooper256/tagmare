package mechanics.input;

/** Short for {@code SimpleRangeHandInquiry}. */
public class SRHInquiry implements HandInquiry {

	private final RangeHandSelection selection;
	private final String displayText;
	
	public SRHInquiry(int min, int max) {
		this(min, max, "");
	}

	public SRHInquiry(int min, int max, String displaySuffix) {
		selection = new RangeHandSelection(min, max);
		if(displaySuffix.isEmpty())
			displayText = String.format("Choose between %d and %d cards.", min, max);
		else
			displayText = String.format("Choose between %d and %d cards %s.", min, max, displaySuffix);
	}

	@Override
	public String displayText() {
		return displayText;
	}

	@Override
	public RangeHandSelection selection() {
		return selection;
	}
	
}
