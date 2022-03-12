package visuals.combat.ribbon;

import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.modifiers.Modifier;
import visuals.Fonts;
import visuals.fxutils.Nodes;

public class Debuffs extends HBox {

	private static final Font FONT = Fonts.GEORGIA_18;
	private static final double SPACING = Buffs.SPACING, PADDING = Buffs.PADDING;

	private final Text title;
	
	public Debuffs() {
		super(SPACING);
		title = Nodes.text("Debuffs >>>", FONT);
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(PADDING));
		getChildren().addAll(title);
	}
	
	public void update() {
		ObservableList<Node> children = getChildren();
		children.subList(1, children.size()).clear();
		for(Modifier m : Hub.player().modifiers())
			if(m.isDebuff())
				children.add(Nodes.text(m.toString(), FONT));
	}
	
}
