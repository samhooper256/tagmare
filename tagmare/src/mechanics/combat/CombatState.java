package mechanics.combat;

public enum CombatState {
	PREP, PLAYER_TURN, PLAYER_TO_ENEMY, ENEMY_TURN,
	/** The combat has finished; there are no enemies left and the player won. */
	WON;
	
}
