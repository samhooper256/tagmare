package base.animations;

import base.Updatable;

public interface Animation extends Updatable {

	void interpolate(double frac);
	
	void start();
	
	void restart();
	
	void pause();
	
	void unpause();
	
	FinisherAnimation withFinisher(Runnable finisher);
	
	boolean isRunning();
	
	boolean isFinished();
	
	boolean isPaused();
	
	long duration();
	
}
