package visuals.combat;

import mechanics.combat.Combat;
import visuals.Vis;

public final class CombatManager {

	private CombatManager() {
		
	}
	
	/** Assumes {@link Combat#startWithoutResuming()} has been called. */
	public static void setupCombat(Combat c) {
		Vis.handLayer().clear();
		Vis.pileLayer().discard().clear();
		Vis.pileLayer().draw().setCards(c.drawPile());
		Vis.infoLayer().energyMeter().setEnergy(0);
		Vis.ribbonLayer().ribbon().buffs().update();
		Vis.ribbonLayer().ribbon().debuffs().update();
		Vis.ribbonLayer().ribbon().shield().update();
		Vis.enemyLayer().setupEnemies(c);
		Vis.winLayer().hide();
	}
	
}
