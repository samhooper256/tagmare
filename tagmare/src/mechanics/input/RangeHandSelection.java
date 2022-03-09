package mechanics.input;

import java.util.List;

import mechanics.cards.Card;

public class RangeHandSelection extends HandSelection implements RangeSelection {

	private final int min, max;
	
	public RangeHandSelection(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean validateFromHand(List<Card> cards) {
		return min() <= cards.size() && cards.size() <= max();
	}
	
	@Override
	public int min() {
		return min;
	}

	@Override
	public int max() {
		return max;
	}
	
}
