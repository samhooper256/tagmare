package mechanics.modifiers;

import java.util.*;

public class ModifierSet implements Iterable<Modifier> {
	
	private final TreeMap<ModifierTag, Modifier> map;
	
	public ModifierSet() {
		map = new TreeMap<>();
	}

	/** Returns the {@link Modifier} in this {@link ModifierSet} with the given {@link ModifierTag}. Returns
	 * {@code null} if there is no such {@link Modifier}. Casts the {@link Modifier} to the given type. Runs in O(n). */
	@SuppressWarnings("unchecked")
	public <M extends Modifier> M getModifier(ModifierTag tag) {
		for(Modifier e : this)
			if(e.tag() == tag)
				return (M) e;
		return null;
	}
	
	/** The returned {@link Modifier} is never {@code null}. Throws an {@link IllegalArgumentException} if this
	 * {@link ModifierSet} has no {@code Modifier} with the given {@link ModifierTag}. */
	@SuppressWarnings("unchecked")
	public <M extends Modifier> M getModifierOrThrow(ModifierTag tag) {
		Modifier e = getModifier(tag);
		if(e == null)
			throw new IllegalArgumentException(String.format("No modifier with tag: %s", tag));
		return (M) e;
	}

	/** Adds the given {@link Modifier} to this {@link ModifierSet}. If this set does not already
	 * {@link #contains(ModifierTag) contain} the modifier's {@link ModifierTag}, this method has the same effect as
	 * {@link #overwrite(Modifier)} and returns {@code true}. Otherwise, if the given modifier is an
	 * {@link Modifier#isInteger() integer} modifier, adds the given modifier's {@link Modifier#integer()} value to the
	 * value of the current modifier that is in this set and returns {@code true}. Otherwise, does nothing and returns
	 * {@code false}. */
	public boolean add(Modifier modifier) {
		if(contains(modifier.tag())) {
			if(modifier.isInteger()) {
				getModifierOrThrow(modifier.tag()).increment(modifier.integer());
				return true;
			}
			//do nothing - this modifier does not stack
			return false;
		}
		else {
			overwrite(modifier);
			return true;
		}
	}
	
	public void overwrite(Modifier modifier) {
		map.put(modifier.tag(), modifier);
	}
	
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	/** Returns {@code true} iff this {@link ModifierSet} has an {@link Modifier} with the given {@link ModifierTag}.
	 * Runs in O(n). */
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
