package visuals.animations;

import java.util.function.DoubleUnaryOperator;

public abstract class Interpolator implements DoubleUnaryOperator {

	public static final Interpolator
		CBRT = Interpolator.fromOperator(Math::cbrt, "CBRT"),
		SQRT = Interpolator.fromOperator(Math::sqrt, "SQRT"),
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
		SQUARED = Interpolator.fromOperator(x -> x * x, "SQUARED");
	
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
	
	public static Interpolator bow(double k) {
		return fromOperator(x -> (1 + k) * x - k * x * x, String.format("BOW(%.2f)", k));
	}
	
	public Interpolator() {
		
	}

}
