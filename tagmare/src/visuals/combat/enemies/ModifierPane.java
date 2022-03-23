package visuals.combat.enemies;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import mechanics.modifiers.*;
import visuals.fxutils.Nodes;

public class ModifierPane extends FlowPane {

	public ModifierPane(EnemyRepresentation er) {
		Nodes.setAllWidths(this, er.getPrefWidth());
		setAlignment(Pos.TOP_LEFT);
		setupModifiers(er.enemy().modifiers());
	}
	
	private void setupModifiers(ModifierSet set) {
		for(Modifier m : set)
			getChildren().add(new EnemyModifierIcon(m));
	}
	
}
