package visuals.combat.enemies.intents;

import mechanics.enemies.intents.IntentPart;

public abstract class LabeledIcon extends IntentPartIcon {

	private final IntentLabel label;
	
	public LabeledIcon(IntentPart intentPart) {
		super(intentPart);
		label = new IntentLabel();
		getChildren().add(label);
	}
	
	public IntentLabel label() {
		return label;
	}
	
}
