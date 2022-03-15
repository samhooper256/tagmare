package visuals.combat.ribbon;

import java.util.List;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import mechanics.Hub;
import mechanics.modifiers.*;
import utils.Strings;
import visuals.Fonts;
import visuals.fxutils.Nodes;

public class Buffs extends HBox {

	public static final Font FONT = Fonts.GEORGIA_18;
	public static final double SPACING = 8, PADDING = 10, HEIGHT = PlayerModifierIcon.HEIGHT;
	
	public static final double Y_IN_RIBBON = 2;
	
	public Buffs() {
		super(SPACING);
		setAlignment(Pos.CENTER_RIGHT);
		setPadding(new Insets(PADDING));
		Nodes.setAllHeights(this, HEIGHT);
		setLayoutY(Y_IN_RIBBON);
	}
	
	public void update() {
		getChildren().clear();
		for(Modifier m : Hub.player().modifiers()) {
			if(m.isBuff()) {
				PlayerModifierIcon icon = PlayerModifierIcon.of(m.tag());
				if(m.isInteger())
					icon.setInteger(m.integer());
				getChildren().add(0, icon);
			}
		}
	}
	
	public void debugPrint(int indent) {
		String tab = Strings.repeat("\t", indent), tab2 = tab + "\t";
		List<Node> children = getChildren();
		System.out.printf("%sBuffs getChildren() (%d)%n", tab, children.size());
		for(Node n : children) {
			if(n instanceof PlayerModifierIcon)
				((PlayerModifierIcon) n).debugPrint(indent + 1);
			else
				System.out.printf("%s%s%n", tab2, n);
		}
	}
	
}
