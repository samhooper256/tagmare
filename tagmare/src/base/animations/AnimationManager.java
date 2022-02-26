package base.animations;

import java.util.*;

import base.Updatable;

public class AnimationManager implements Updatable {

	private static final AnimationManager INSTANCE = new AnimationManager();
	
	public static AnimationManager get() {
		return INSTANCE;
	}
	
	private final List<Animation> animations;
	
	private AnimationManager() {
		animations = new ArrayList<>();
	}

	@Override
	public void update(long diff) {
		for(int i = 0; i < animations.size(); ) {
			Animation a = animations.get(i);
			a.update(diff);
			if(a.isFinished())
				animations.remove(i);
			else
				i++;
		}
	}
	
	public void add(Animation animation) {
		animations.add(animation);
	}
	
}
