package visuals.combat.enemies.intents;

import javafx.geometry.Side;
import mechanics.enemies.Enemy;
import mechanics.enemies.intents.IntentPart;
import visuals.Sprite;
import visuals.fxutils.Images;
import visuals.tooltips.*;

public abstract class IntentPartIcon extends Sprite implements TooltipAware {

	public static final double SIZE = 48;
	
	private final IntentPart intentPart;
	private final TooltipManager tooltipManager;
	private final Tooltip tooltip;
	
	public IntentPartIcon(IntentPart intentPart) {
		super(Images.forIntentPart(intentPart));
		this.intentPart = intentPart;
		tooltipManager = new TooltipManager(this, Side.RIGHT);
		tooltip = Tooltip.untitled();
		tooltipManager.column().add(tooltip);
		tooltipManager.install();
	}

	/** Can be overridden; overriding methods must call this one. */
	public void update(Enemy enemy) {
		tooltip.setDescription(intentPart().description());
	}

	@Override
	public TooltipManager tooltipManager() {
		return tooltipManager;
	}
	
	public IntentPart intentPart() {
		return intentPart;
	}
	
}
