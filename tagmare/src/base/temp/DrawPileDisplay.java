package base.temp;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.Hub;
import mechanics.cards.Card;

public class DrawPileDisplay extends CardListDisplay {

	DrawPileDisplay() {
		super("Draw Pile:");
	}

	@Override
	Iterable<Card> cards() {
		List<Card> list = new ArrayList<>(Hub.drawPile().trueOrder());
		Collections.reverse(list);
		return list;
	}
	
	@Override
	void prettify(Text text) {
		text.setFill(Color.BLUE);
	}

}
