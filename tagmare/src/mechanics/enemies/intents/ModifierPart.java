package mechanics.enemies.intents;

import mechanics.modifiers.Modifier;

public abstract class ModifierPart implements IntentPart {

	private final Modifier modifier;
	
	/** The called is trusted <em>not to change the given {@link Modifier} after it is passed to this
	 * constructor.</em> */
	public ModifierPart(Modifier modifier) {
		this.modifier = modifier;
	}
	
	public Modifier modifier() {
		return modifier;
	}
	
}
