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
	
}
