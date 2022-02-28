package mechanics.actions.list;

import java.util.*;

import mechanics.actions.Action;

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
	
	/** Once built, no other methods should be invoked from this {@link ActionListBuilder}. */
	public ActionList build() {
		ActionList list = ActionList.ofTrusted(actions);
		actions = null;
		return list;
	}
	
}
