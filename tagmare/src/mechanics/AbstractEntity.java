package mechanics;

import mechanics.modifiers.ModifierSet;

public abstract class AbstractEntity implements Entity {

	private final ModifierSet modifiers;
	private final Health health;
	private final Block block;
	
	public AbstractEntity(int maxHealth) {
		modifiers = new ModifierSet();
		health = new Health(maxHealth);
		block = new Block();
	}
	
	@Override
	public final Health health() {
		return health;
	}

	@Override
	public Block block() {
		return block;
	}
	
	@Override
	public final ModifierSet modifiers() {
		return modifiers;
	}
	
}
