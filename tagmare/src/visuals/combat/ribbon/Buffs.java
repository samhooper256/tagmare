package visuals.combat.ribbon;

import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.modifiers.*;
import visuals.Fonts;
import visuals.fxutils.Nodes;

public class Buffs extends HBox {

	public static final Font FONT = Fonts.GEORGIA_18;
	public static final double SPACING = 8, PADDING = 10;
	
	private final Text title;
	
	public Buffs() {
		super(SPACING);
		title = Nodes.text("<<< Buffs", FONT);
		setAlignment(Pos.CENTER_RIGHT);
		setPadding(new Insets(PADDING));
		getChildren().add(title);
		update();
	}
	
	public void update() {
		clear();
		for(Modifier m : Hub.player().modifiers()) {
			if(m.isBuff()) {
				PlayerModifierIcon icon = PlayerModifierIcon.of(m.tag());
				if(m.isInteger())
					icon.setInteger(m.integer());
				getChildren().add(0, icon);
			}
		}
	}
	
	public void clear() {
		ObservableList<Node> children = getChildren();
		children.subList(0, children.size() - 1).clear();
	}
	
}
