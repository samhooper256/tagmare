package visuals.combat;

import mechanics.modifiers.*;

public interface ModifierIcon {

	ModifierTag tag();
	
	/** Changes the integer displayed; does <em>not</em> change the {@link Modifier#integer() integer} of the underlying
	 * {@link Modifier} being represented by this {@link ModifierIcon}.
	 * @throws UnsupportedOperationException if {@link #tag()} does not represent an
	 * {@link ModifierTag#isIntegerModifier() integer modifier}. */
	void setInteger(int integer);
	
}
