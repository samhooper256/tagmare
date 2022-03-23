package visuals.combat.enemies;

import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import mechanics.modifiers.*;
import visuals.fxutils.Nodes;

public class ModifierPane extends FlowPane {

	private static final double SPACING = 4;
	
	public ModifierPane(EnemyRepresentation er) {
		Nodes.setAllWidths(this, er.getPrefWidth());
		setAlignment(Pos.TOP_LEFT);
		this.setHgap(SPACING);
		this.setVgap(SPACING);
		setupModifiers(er.enemy().modifiers());
	}
	
	private void setupModifiers(ModifierSet set) {
		for(Modifier m : set)
			getChildren().add(new EnemyModifierIcon(m));
	}

	public void update(ModifierSet set) {
		Iterator<Node> itr = getChildren().iterator();
		while(itr.hasNext()) {
			if(!set.contains(((EnemyModifierIcon) itr.next()).tag()))
				itr.remove();
		}
		for(Modifier m : set) {
			EnemyModifierIcon emi = icon(m.tag());
			if(emi != null) {
				if(m.isInteger())
					emi.setInteger(m.integer());
			}
			else {
				getChildren().add(new EnemyModifierIcon(m));
			}
		}
	}
	
	public boolean contains(ModifierTag tag) {
		return icon(tag) != null;
	}
	
	public EnemyModifierIcon iconOrThrow(ModifierTag tag) {
		EnemyModifierIcon emi = icon(tag);
		if(emi == null)
			throw new IllegalStateException(String.format("ModifierTag not present: %s", tag));
		return emi;
	}
	
	/** {@code null} if not present. */
	public EnemyModifierIcon icon(ModifierTag tag) {
		for(Node n : getChildren()) {
			EnemyModifierIcon emi = ((EnemyModifierIcon) n);
			if(emi.tag() == tag)
				return emi;
		}
		return null;
	}
	
}
