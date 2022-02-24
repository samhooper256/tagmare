package effects;

import java.util.*;

public class EffectSet implements Iterable<Effect> {
	
	private final TreeMap<EffectTag, Effect> map;
	
	public EffectSet() {
		map = new TreeMap<>();
	}

	/** Returns the {@link Effect} in this {@link EffectSet} with the given {@link EffectTag}. Returns {@code null} if
	 * there is no such {@link Effect}. Runs in O(n). */
	public Effect getEffect(EffectTag tag) {
		for(Effect e : this)
			if(e.tag() == tag)
				return e;
		return null;
	}
	
	/** The returned {@link Effect} is never {@code null}. Throws an {@link IllegalArgumentException} if this
	 * {@link EffectSet} has no {@code Effect} with the given {@link EffectTag}. */
	public Effect getEffectOrThrow(EffectTag tag) {
		Effect e = getEffect(tag);
		if(e == null)
			throw new IllegalArgumentException(String.format("No effect with tag: %s", tag));
		return e;
	}
	
	public void overwrite(Effect e) {
		map.put(e.tag(), e);
	}
	
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	/** Returns {@code true} iff this {@link EffectSet} has an {@link Effect} with the given {@link EffectTag}. Runs
	 * in O(n). */
	public boolean contains(EffectTag tag) {
		return getEffect(tag) != null;
	}
	
	public void clear() {
		map.clear();
	}
	
	@Override
	public Iterator<Effect> iterator() {
		return map.values().iterator();
	}
	
}
