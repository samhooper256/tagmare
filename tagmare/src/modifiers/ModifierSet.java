package modifiers;

import java.util.*;

public class ModifierSet implements Iterable<Modifier> {
	
	private final TreeMap<ModifierTag, Modifier> map;
	
	public ModifierSet() {
		map = new TreeMap<>();
	}

	/** Returns the {@link Modifier} in this {@link ModifierSet} with the given {@link ModifierTag}. Returns {@code null} if
	 * there is no such {@link Modifier}. Runs in O(n). */
	public Modifier getModifier(ModifierTag tag) {
		for(Modifier e : this)
			if(e.tag() == tag)
				return e;
		return null;
	}
	
	/** The returned {@link Modifier} is never {@code null}. Throws an {@link IllegalArgumentException} if this
	 * {@link ModifierSet} has no {@code Modifier} with the given {@link ModifierTag}. */
	public Modifier getModifierOrThrow(ModifierTag tag) {
		Modifier e = getModifier(tag);
		if(e == null)
			throw new IllegalArgumentException(String.format("No modifier with tag: %s", tag));
		return e;
	}
	
	public void overwrite(Modifier e) {
		map.put(e.tag(), e);
	}
	
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	/** Returns {@code true} iff this {@link ModifierSet} has an {@link Modifier} with the given {@link ModifierTag}. Runs
	 * in O(n). */
	public boolean contains(ModifierTag tag) {
		return getModifier(tag) != null;
	}
	
	public void clear() {
		map.clear();
	}
	
	@Override
	public Iterator<Modifier> iterator() {
		return map.values().iterator();
	}
	
}
