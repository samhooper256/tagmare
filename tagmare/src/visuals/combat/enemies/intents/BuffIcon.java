package visuals.combat.enemies.intents;

import mechanics.enemies.intents.*;

public class BuffIcon extends IntentPartIcon {

	public BuffIcon(BuffPart buffPart) {
		super(buffPart);
	}
	
	@Override
	public BuffPart intentPart() {
		return (BuffPart) super.intentPart();
	}
	
}
