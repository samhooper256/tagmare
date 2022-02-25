package mechanics;

import java.util.ArrayList;

import mechanics.actions.*;

public class ActionStack {

	private final ArrayList<Action> list;
	
	public ActionStack() {
		list = new ArrayList<>();
	}
	
	public void push(Action a) {
		list.add(a);
	}
	
	/** {@link #push(Action) pushes} the given {@link Action Actions} in the given order. The last item passed will be
	 * on top of this {@link ActionStack} when this method returns. */
	public void push(Action... actions) {
		for(Action a : actions)
			push(a);
	}
	
	/** {@link #push(Action) pushes} the given {@link Action Actions} in the <em>reverse</em> of the given order.
	 * The first item passed will be on top of this {@link ActionStack} when this method returns. */
	public void pushReversed(Action... actions) {
		for(int i = actions.length - 1; i >= 0; i--)
			push(actions[i]);
	}

	public void push(ActionList list) {
		for(Action a : list)
			push(a);
	}
	
	public void pushReversed(ActionList list) {
		for(int i = list.size() - 1; i >= 0; i--)
			push(list.get(i));
	}
	
	/** Throws an exception if empty. */
	public Action pop() {
		return list.remove(list.size() - 1);
	}
	
	/** Throws an exception if empty. */
	public Action peek() {
		return list.get(list.size() - 1);
	}
	
	public Action peekOrNull() {
		return list.isEmpty() ? null : peek();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
}
