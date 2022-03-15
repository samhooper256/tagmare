package visuals.combat;

import mechanics.Hub;
import visuals.Vis;
import visuals.gallery.Gallery;

public class ForesightGallery extends Gallery {

	public ForesightGallery() {
		super("Draw Pile", "Cards are shown in the order they will be drawn.",
				() -> Vis.manager().checkedResumeFromAnimation());
	}
	
	@Override
	public void startIntro() {
		setCards(Hub.drawPile().descendingIterator());
		super.startIntro();
	}
	
}
