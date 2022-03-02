package visuals.animations;

import java.util.function.DoubleUnaryOperator;

public abstract class Interpolator implements DoubleUnaryOperator {

	public static final Interpolator
		LINEAR = new Interpolator() {
		
			@Override
			public double applyAsDouble(double operand) {
				return operand;
			}
			
			@Override
			public String toString() {
				return "LINEAR";
			}
			
		},
		SQRT = Interpolator.fromOperator(Math::sqrt, "SQRT"),
		CBRT = Interpolator.fromOperator(Math::cbrt, "CBRT"),
		BOW_OUT_1 = Interpolator.fromOperator(x -> 1.1 * x - .1 * x * x, "BOW_OUT_1");
	
	public static Interpolator fromOperator(DoubleUnaryOperator duo) {
		return new Interpolator() {
			
			@Override
			public double applyAsDouble(double operand) {
				return duo.applyAsDouble(operand);
			}
			
		};
	}
	
	private static Interpolator fromOperator(DoubleUnaryOperator duo, String name) {
		return new Interpolator() {
			
			@Override
			public double applyAsDouble(double operand) {
				return duo.applyAsDouble(operand);
			}
			
			@Override
			public String toString() {
				return name;
			}
			
		};
	}
	
	public Interpolator() {
		
	}

}
