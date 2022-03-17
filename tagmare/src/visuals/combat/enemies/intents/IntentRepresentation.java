package visuals.combat.enemies.intents;

import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import mechanics.enemies.Enemy;
import mechanics.enemies.intents.*;
import visuals.fxutils.*;

public final class IntentRepresentation extends HBox {

	public static final double HEIGHT = IntentPartIcon.SIZE;
	
	private static final double SPACING = 8;
	
	private static final WeakHashMap<Intent, IntentRepresentation> MAP = new WeakHashMap<>();
	
	public static IntentRepresentation of(Intent intent) {
		if(!MAP.containsKey(intent))
			MAP.put(intent, new IntentRepresentation(intent));
		return MAP.get(intent);
	}
	
	private static List<IntentPartIcon> createIcons(Intent intent) {
		List<IntentPart> parts = intent.parts();
		ArrayList<IntentPartIcon> icons = new ArrayList<>(parts.size());
		for(IntentPart ip : parts)
			icons.add(createIcon(ip));
		return icons;
	}
	
	private static IntentPartIcon createIcon(IntentPart ip) {
		if(ip instanceof AttackPart)
			return new AttackIcon((AttackPart) ip);
		else if(ip instanceof BlockPart)
			return new BlockIcon((BlockPart) ip);
		else if(ip instanceof DebuffPart)
			return new DebuffIcon((DebuffPart) ip);
		else if(ip instanceof BuffPart)
			return new BuffIcon((BuffPart) ip);
		else
			throw new UnsupportedOperationException(String.format("IntentPart: %s", ip));
	}
	
	private final Intent intent;
	
	private IntentRepresentation(Intent intent) {
		super(SPACING);
		this.intent = intent;
		setAlignment(Pos.CENTER);
		Nodes.setAllHeights(this, HEIGHT);
		getChildren().addAll(createIcons(intent));
	}

	public void update(Enemy enemy) {
		for(Node n : getChildren())
			((IntentPartIcon) n).update(enemy);
	}

	public List<Node> intentIcons() {
		return getChildren();
	}
	
	public Intent intent() {
		return intent;
	}
	
}
