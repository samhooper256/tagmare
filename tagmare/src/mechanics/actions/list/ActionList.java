package mechanics.actions.list;

import java.util.*;

import mechanics.actions.Action;

/** Unmodifiable. */
public class ActionList implements Iterable<Action> {

	public static final ActionList EMPTY = new ActionList();
	
	public static ActionListBuilder builder() {
		return new ActionListBuilder();
	}
	
	public static ActionList of(Action... actions) {
		if(actions.length == 0)
			return EMPTY;
		return new ActionList(actions);
	}
	
	/** Defensively copies. */
	public static ActionList of(List<Action> actions) {
		if(actions.isEmpty())
			return EMPTY;
		return new ActionList(new ArrayList<>(actions));
	}
	
	static ActionList ofTrusted(List<Action> actions) {
		if(actions.isEmpty())
			return EMPTY;
		return new ActionList(actions);
	}
	
	/** unmodifiable */
	private final List<Action> list;
	
	private ActionList(Action... actions) {
		list = Collections.unmodifiableList(Arrays.asList(actions));
	}

	/** <em><strong>DOES NOT defensively copy. The given list should not be modified after being passed to this
	 * constructor. </strong></em>*/
	private ActionList(List<Action> actions) {
		list = Collections.unmodifiableList(actions);
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
	
	@Override
	public String toString() {
		return list.toString();
	}
	
}