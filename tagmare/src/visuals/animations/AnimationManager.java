package visuals.animations;

import java.util.*;

import base.Updatable;

/** This class trusts that the same animation is not re-added when it is already in this {@link AnimationManager}.
 * {@link Animation Animations} are automatically removed when they are {@link Animation#isFinished() finished}. */
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

	public void addAll(Animation... animations) {
		for(Animation animation : animations)
			add(animation);
	}

	public void addAll(List<Animation> animations) {
		for(Animation animation : animations)
			add(animation);
	}
	
}
