package visuals.combat.enemies.intents;

import mechanics.enemies.intents.*;

public class DebuffIcon extends IntentPartIcon {

	public DebuffIcon(DebuffPart debuffPart) {
		super(debuffPart);
	}
	
	@Override
	public DebuffPart intentPart() {
		return (DebuffPart) super.intentPart();
	}
	
}
