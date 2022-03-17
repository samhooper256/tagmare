package mechanics.enemies.intents;

import java.util.*;
import java.util.function.Predicate;

import mechanics.actions.Action;
import mechanics.actions.list.*;
import mechanics.enemies.Enemy;

import static mechanics.enemies.intents.IntentStaticMethods.*;

/** <p>An {@link Intent} represents what an {@link Enemy} intends to do (that is, what its action(s) will be on its turn).
 * Every enemy always has an {@code Intent}, which has a (possibly empty) list of {@link #parts()}. The parts are
 * executed in order.</p>
 * <p>{@code Intents} are immutable.</p>*/
public interface Intent {

	public Intent EMPTY = withImmutableParts(Collections.emptyList());
	
	public static Intent withParts(IntentPart... parts) {
		return withParts(Arrays.asList(parts));
	}
	
	/** The given {@link List} is defensively copied. */
	public static Intent withParts(List<IntentPart> parts) {
		return withUnchangingParts(new ArrayList<>(parts));
	}
	
	List<IntentPart> parts();
	
	default ActionList getActions(Enemy enemy) {
		ActionListBuilder list = Action.listBuilder();
		for(IntentPart p : parts())
			list.addAll(p.getActions(enemy));
		return list.build();
	}
	
	default boolean hasAttack() {
		for(IntentPart p : parts())
			if(p instanceof AttackPart)
				return true;
		return false;
	}
	
	default Intent without(Predicate<? super IntentPart> condition) {
		List<IntentPart> list = null;
		List<IntentPart> parts = parts();
		for(int i = 0; i < parts.size(); i++) {
			IntentPart p = parts.get(i);
			if(condition.test(p)) {
				if(list == null) {
					list = new ArrayList<>();
					for(int j = 0; j < i; j++)
						list.add(parts.get(j));
				}
			}
			else if(list != null) {
				list.add(p);
			}
		}
		return list == null ? this : withUnchangingParts(list);
	}
	
}
