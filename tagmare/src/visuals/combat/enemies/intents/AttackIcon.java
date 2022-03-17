package visuals.combat.enemies.intents;

import mechanics.enemies.Enemy;
import mechanics.enemies.intents.AttackPart;

public class AttackIcon extends LabeledIcon {
	
	public AttackIcon(AttackPart attackPart) {
		super(attackPart);
	}
	
	@Override
	public void update(Enemy enemy) {
		super.update(enemy);
		label().setText(String.valueOf(intentPart().getModifiedInteger(enemy)));
	}
	
	@Override
	public AttackPart intentPart() {
		return (AttackPart) super.intentPart();
	}
	
}
