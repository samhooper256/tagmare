package visuals.combat.enemies.intents;

import mechanics.enemies.intents.*;
import visuals.fxutils.Images;

public class StrikeIcon extends LabeledIcon {
	
	private final Strike strike;
	
	public StrikeIcon(Strike strike) {
		super(Images.SWORD_INTENT);
		this.strike = strike;
		update();
	}
	
	@Override
	public void update() {
		label().setText(String.valueOf(strike.damage()));
	}
	
}
