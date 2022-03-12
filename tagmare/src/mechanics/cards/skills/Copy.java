package mechanics.cards.skills;

import mechanics.actions.*;
import mechanics.actions.list.ActionList;
import mechanics.cards.*;
import mechanics.cards.singed.Guilt;
import mechanics.effects.SkillEffects;
import mechanics.enemies.Enemy;
import mechanics.modifiers.buffs.Cheating;

public class Copy extends AbstractCard implements Skill {

	public Copy() {
		super(CardTag.COPY);
	}

	@Override
	public Card copy() {
		return new Copy();
	}

	@Override
	public ActionList generateActions(Enemy target) {
		return SkillEffects.apply(this,
			ApplyModifier.toPlayer(new Cheating(1), this),
			new PlaceCardOnTopOfDrawPile(new Guilt(), this)
		);
	}
	
}
