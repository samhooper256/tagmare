package mechanics.actions;

import java.util.*;

/** Unmodifiable. */
public class ActionList implements Iterable<Action> {

	public static final ActionList EMPTY = new ActionList();
	
	/** unmodifiable */
	private final List<Action> list;
	
	public ActionList(Action... actions) {
		list = Collections.unmodifiableList(Arrays.asList(actions));
	}

	@Override
	public Iterator<Action> iterator() {
		return list.iterator();
	}
	
	public Action get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
}
