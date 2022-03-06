package visuals;

import base.VisualManager;
import mechanics.Hub;
import mechanics.actions.*;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import visuals.enemies.EnemyRepresentation;
import visuals.ribbon.BottomRibbon;

public final class VisualManagerImpl implements VisualManager {

	private boolean waitingOnAnimation;
	
	@Override
	public void executeAction(final Action action) {
		waitingOnAnimation = true;
		Vis.debugLayer().stackDisplay().update();
		if(action instanceof SimpleDrawRequest) {
			Hub.combat().pause();
			action.execute();
			SimpleDrawRequest a = (SimpleDrawRequest) action;
			Card card = a.getCard();
			if(card != null)
				Vis.handLayer().startAddCardToRightAnimation(card);
			else
				Hub.combat().resume();
		}
		else if(action instanceof EOTDiscard || action instanceof ExplicitDiscard) {
			Hub.combat().pause();
			action.execute();
			Card card = ((HasCard) action).card();
			CardRepresentation.of(card).startEOTToDiscard();
		}
		else if(action instanceof RemoveOTFromPlay) {
			Hub.combat().pause();
			action.execute();
			Card card = ((HasCard) action).card();
			CardRepresentation.of(card).startRemoveOT();
		}
		else if(action instanceof SetEnergy || action instanceof SpendEnergy) {
			Hub.combat().pause();
			action.execute();
			Vis.infoLayer().energyMeter().startEnergyChangeAnimation(Hub.energy().amount());
		}
		else if(action instanceof RefillDrawPile) {
			action.execute();
			Vis.pileLayer().draw().setCards(Hub.drawPile().trueOrder()); //TODO some kind of animation for this?
		}
		else if(action instanceof NaturalDiscard) {
			Hub.combat().pause();
			action.execute();
			Vis.handLayer().startNaturalDiscard();
		}
		else if(action instanceof DealDamage || action instanceof DealDamageToAll ||
				action instanceof UpdateEnemyIntents || action instanceof ProcrastinatedDamage ||
				action instanceof ApplyModifier || action instanceof ChangeModifier ||
				action instanceof RemoveModifier) {
			waitingOnAnimation = false;
			action.execute();
			updateAllEnemies();
			Vis.ribbonLayer().bottom().updateModifiers();
		}
		else if(action instanceof ClearEnemy) {
			waitingOnAnimation = false;
			action.execute();
			Vis.enemyLayer().updateEnemiesShown();
		}
		else if(action instanceof TakeDamage || action instanceof GainBlock) {
			waitingOnAnimation = false;
			action.execute();
			BottomRibbon br = Vis.ribbonLayer().bottom();
			br.healthBar().update();
			br.shield().update();
		}
		else {
			waitingOnAnimation = false;
			action.execute();
		}
	}

	private void updateAllEnemies() {
		for(Enemy e : Hub.enemies())
			EnemyRepresentation.of(e).update();
	}
	@Override
	public boolean requestPlayCardFromHand(Card card, Enemy target) {
		return false; //TODO
	}
	
	@Override
	public void playCardFromHand(Card card, Enemy target) {
		if(Hub.combat().running() || !card.isLegal(target))
			throw new IllegalArgumentException(String.format("Can't play. card=%s, target=%s", card, target));
		Hub.combat().stackPlayCardFromHand(card, target);
		Hub.combat().resume();
	}
	
	@Override
	public void update(long diff) {
		GameScene.get().update(diff);
		VisualManager.super.update(diff);
	}
	
	@Override
	public void checkedResumeFromAnimation() {
		if(Hub.combat().running())
			throw new IllegalStateException("Cannot resume; Combat is running");
		waitingOnAnimation = false;
		Hub.combat().resume();
	}
	
	@Override
	public boolean waitingOnAnimation() {
		return waitingOnAnimation;
	}

}
