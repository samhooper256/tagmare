package visuals.combat;

import mechanics.combat.Combat;
import visuals.Vis;

public final class CombatManager {

	private CombatManager() {
		
	}
	
	public static void setupCombat(Combat c) {
		Vis.pileLayer().draw().setCards(c.drawPile());
		Vis.enemyLayer().setupEnemies(c);
	}
	
}
