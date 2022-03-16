package mechanics.actions.list;

import java.util.*;

import mechanics.actions.Action;

/** Should only be built once. Behavior is undefined if used after {@link #build()} has been called. */
public final class ActionListBuilder {

	private List<Action> actions;
	
	ActionListBuilder() {
		actions = new ArrayList<>();
	}
	
	public void add(Action action) {
		if(actions == null)
			throw new IllegalStateException("This builder has already been used (built).");
		actions.add(action);
	}
	
	public void addAll(Action... actions) {
		for(Action a : actions)
			add(a);
	}
	
	public void addAll(ActionList actions) {
		for(Action a : actions)
			add(a);
	}
	
	/** Once built, no other methods should be invoked from this {@link ActionListBuilder}. */
	public ActionList build() {
		ActionList list = ActionList.ofTrusted(actions);
		actions = null;
		return list;
	}
	
}
