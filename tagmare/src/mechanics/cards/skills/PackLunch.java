package mechanics.cards.skills;

import mechanics.actions.ApplyModifier;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.Packed;

public class PackLunch extends AbstractCard implements Skill {

	public PackLunch() {
		super(CardTag.PACK_LUNCH);
	}
	
	@Override
	public Card copy() {
		return new PackLunch();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this, ApplyModifier.toPlayer(new Packed(1), this));
	}
	
}
