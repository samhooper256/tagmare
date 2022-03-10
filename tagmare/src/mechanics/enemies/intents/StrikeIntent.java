package mechanics.enemies.intents;

import java.util.*;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.enemies.Enemy;

/** Consists of one or more {@link Strike Strikes}. */
public class StrikeIntent implements AttackIntent {

	/** Unmodifiable. */
	private final List<Strike> strikes;
	
	public StrikeIntent(int... strikes) {
		ArrayList<Strike> list = new ArrayList<>(strikes.length);
		for(int damage : strikes)
			list.add(new Strike(damage));
		this.strikes = Collections.unmodifiableList(list);
	}
	
	@Override
	public ActionList getActions(Enemy enemy) {
		Action[] actions = new Action[strikes.size()];
		for(int i = 0; i < strikes.size(); i++)
			actions[i] = new TakeDamage(strikes.get(i).damage(), enemy);
		return Action.list(actions);
	}
	
	/** Unmodifiable. */
	public List<Strike> strikes() {
		return strikes;
	}
	
	@Override
	public String toString() {
		StringJoiner j = new StringJoiner(", ", "{", "}");
		for(Strike strike : strikes())
			j.add(strike.toString());
		return j.toString();
	}

}