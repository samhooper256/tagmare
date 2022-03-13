package utils;

public final class Nums {

	private Nums() {
		
	}
	
	public static double clamp(double value, double min, double max) {
		if(value < min)
			value = min;
		else if(value > max)
			value = max;
		return value;
	}
	
	public static double lerp(double frac, double a, double b) {
		return a + (b - a) * frac;
	}
	
}
