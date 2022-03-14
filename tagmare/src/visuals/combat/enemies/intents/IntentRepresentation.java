package visuals.combat.enemies.intents;

import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import mechanics.enemies.intents.*;
import visuals.fxutils.*;

public final class IntentRepresentation extends HBox {

	public static final double HEIGHT = IntentIcon.SIZE;
	
	private static final double SPACING = 8;
	
	private static final WeakHashMap<Intent, IntentRepresentation> MAP = new WeakHashMap<>();
	
	public static IntentRepresentation of(Intent intent) {
		if(!MAP.containsKey(intent))
			MAP.put(intent, new IntentRepresentation(intent));
		return MAP.get(intent);
	}
	
	private static List<IntentIcon> createIcons(Intent intent) {
		if(intent instanceof StrikeIntent) {
			List<IntentIcon> icons = new ArrayList<>();
			for(Strike s : ((StrikeIntent) intent).strikes())
				icons.add(new StrikeIcon(s));
			return icons;
		}
		else if(intent instanceof BasicBlock) {
			return Arrays.asList(new BlockIcon((BasicBlock) intent));
		}
		else if(intent instanceof BlockStrike) {
			return Arrays.asList(new BlockStrikeIcon((BlockStrike) intent));
		}
		else if(intent instanceof DoNothing) {
			return Arrays.asList(new UnlabeledIcon(Images.DO_NOTHING_INTENT));
		}
		else {
			throw new UnsupportedOperationException(String.format("Intent: %s", intent));
		}
	}
	
	private final Intent intent;
	
	private IntentRepresentation(Intent intent) {
		super(SPACING);
		this.intent = intent;
		setAlignment(Pos.CENTER);
		Nodes.setPrefAndMaxHeight(this, HEIGHT);
		getChildren().addAll(createIcons(intent));
	}

	public void update() {
		for(Node n : getChildren())
			((IntentIcon) n).update();
	}

	public List<Node> intentIcons() {
		return getChildren();
	}
	
	public Intent intent() {
		return intent;
	}
	
}
