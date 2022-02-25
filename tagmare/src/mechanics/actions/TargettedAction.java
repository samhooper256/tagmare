package mechanics.actions;

import mechanics.*;

/** {@link Action Actions} that sometimes have a target and sometimes don't may implement this interface;
 * {@link #target()} should return {@code null} if no target. */
public interface TargettedAction extends Action {

	/** Immutable. Returns the same object every time. */
	Enemy target();
	
}
