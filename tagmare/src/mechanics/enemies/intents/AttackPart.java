package mechanics.enemies.intents;

import mechanics.actions.TakeDamage;
import mechanics.actions.list.ActionList;
import mechanics.effects.EnemyAttackEffects;
import mechanics.enemies.Enemy;

/** A single piece of damage dealt from an enemy. Damage dealt by an {@link AttackPart} happens in a single hit.
 * This class is immutable. */
public final class AttackPart extends IntegerPart {
	
	public AttackPart(int damage) {
		super(damage);
	}

	@Override
	public ActionList getActions(Enemy enemy) {
		return EnemyAttackEffects.apply(enemy, new TakeDamage(damage(), enemy));
	}
	
	@Override
	public int getModifiedInteger(Enemy enemy) {
		return EnemyAttackEffects.getModifiedDamage(enemy, damage());
	}

	public int damage() {
		return integer();
	}
	
	@Override
	public IntentPartTag tag() {
		return IntentPartTag.ATTACK;
	}

	@Override
	public String toString() {
		return String.format("%s[%d]", getClass().getSimpleName(), damage());
	}
	
}
