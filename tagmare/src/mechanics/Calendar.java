package mechanics;

import java.util.*;

import mechanics.combat.Combat;
import mechanics.enemies.*;

public final class Calendar {

	public static final int WEEKS = 36;
	
	private final List<Combat> combats;
	
	private int index;
	
	public Calendar() {
		List<Combat> combatsModifiable = new ArrayList<>();
		for(int i = 0; i < WEEKS; i++) {
			if(i == 0)
				combatsModifiable.add(new Combat(new CalculusPracticeQuiz(), new GovernmentAMSCO(), new APESProgressCheck()));
			else
				combatsModifiable.add(new Combat());
		}
		combats = Collections.unmodifiableList(combatsModifiable);
		index = 0;
	}
	
	/** Unmodifiable. */
	public List<Combat> combats() {
		return combats;
	}
	
	/** Equivalent to {@code combats().get(index())}.*/
	public Combat combat() {
		return combats.get(index());
	}
	
	/** The index of the current (or next, if the player is not currently in combat) {@link Combat} in
	 * {@link #combats()}. 0-based. */
	public int index() {
		return index;
	}
	
	public boolean hasMoreWeeks() {
		return index < WEEKS - 1;
	}
	
	public void incrementIndex() {
		if(!hasMoreWeeks())
			throw new IllegalStateException(String.format("No more weeks; index=%d", index));
		index++;
	}
	
}
