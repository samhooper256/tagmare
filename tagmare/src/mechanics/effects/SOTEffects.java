package mechanics.effects;

import mechanics.enemies.Enemy;
import mechanics.modifiers.*;
import mechanics.modifiers.buffs.*;
import mechanics.modifiers.mixed.Motivation;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.actions.list.*;

import static mechanics.modifiers.ModifierTag.*;

public final class SOTEffects {

	private SOTEffects() {
		
	}
	
	public static ActionList apply() {
		ActionListBuilder list = Action.listBuilder();
		ModifierSet pmods = Hub.player().modifiers();
		if(pmods.contains(TIRED)) {
			Modifier tired = pmods.getModifierOrThrow(TIRED);
			list.add(new ChangeEnergy(-tired.integer(), tired));
			list.add(RemoveModifier.fromPlayer(TIRED, null));
		}
		if(pmods.contains(CLOCKED)) {
			Modifier r = pmods.getModifierOrThrow(CLOCKED);
			list.add(new ChangeEnergy(r.integer(), r));
			list.add(RemoveModifier.fromPlayer(CLOCKED, null));
		}
		if(pmods.contains(PACKED)) {
			Modifier p = pmods.getModifierOrThrow(PACKED);
			list.add(new ChangeEnergy(p.integer(), p));
			for(int i = 0; i < p.integer(); i++)
				list.add(new SimpleDrawRequest(p));
			list.add(RemoveModifier.fromPlayer(PACKED, null));
		}
		if(pmods.contains(PLANNING_AHEAD)) {
			Modifier pa = pmods.getModifierOrThrow(PLANNING_AHEAD);
			list.add(new GainBlock(pa.integer(), pa));
			list.add(RemoveModifier.fromPlayer(PLANNING_AHEAD, null));
		}
		if(pmods.contains(ON_LEAVE)) {
			Modifier ol = pmods.getModifierOrThrow(ON_LEAVE);
			list.add(ApplyModifier.toPlayer(new Concentration(ol.integer()), ol));
			list.add(RemoveModifier.fromPlayer(ON_LEAVE, null));
		}
		if(pmods.contains(MENTAL_EXPANSION)) {
			MentalExpansion me = pmods.getModifierOrThrow(MENTAL_EXPANSION);
			list.add(ApplyModifier.toPlayer(new Motivation(me.integer()), me));
		}
		if(pmods.contains(ENLIGHTENED)) {
			Enlightened e = pmods.getModifierOrThrow(ENLIGHTENED);
			list.add(ApplyModifier.toPlayer(new MentalExpansion(e.integer()), e));
		}
		for(Enemy e : Hub.enemies())
			if(e.modifiers().contains(PROCRASTINATED))
				list.add(new ProcrastinatedDamage(e.modifiers().getModifierOrThrow(PROCRASTINATED), e));
		for(Enemy e : Hub.enemies())
			if(e.modifiers().contains(PROCRASTINATED))
				list.add(new RemoveModifier(PROCRASTINATED, null, e));
		return list.build();
	}
	
}
