package visuals.combat.enemies;

import java.util.function.DoubleUnaryOperator;

import visuals.Colors;

public class Slice extends NumberKnock {

	public static final DoubleUnaryOperator X = t -> 20 * t, Y = t -> 32 * t * (t - .9);
	
	private static final double NX0 = 20, NY0 = 0; //initial x and y coordinates.
	
	public Slice(int damage) {
		super(NX0, NY0, X, Y, Colors.DAMAGE_KNOCKED);
		setDamage(damage);
	}

}
