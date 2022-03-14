package visuals.combat.enemies;

import java.util.function.DoubleUnaryOperator;

import visuals.Colors;

/** When you knock off some block. */
public class Chip extends NumberKnock {
	
	public static final DoubleUnaryOperator X = t -> -Slice.X.applyAsDouble(t), Y = Slice.Y;
	
	private static final double NX0 = 0, NY0 = 0;
	
	protected Chip(int damage) {
		super(NX0, NY0, X, Y, Colors.BLOCK_KNOCKED);
		setDamage(damage);
	}
	
}
