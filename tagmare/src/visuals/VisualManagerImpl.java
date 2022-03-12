package visuals;

import java.util.*;

import base.VisualManager;
import mechanics.*;
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
		if(action instanceof PutCardInPlay) {
			action.execute();
			Card card = ((HasCard) action).card();
			Vis.handLayer().moveSelectedToInPlay(card);
		}
		else if(action instanceof PutBypassedCardInPlay) {
			Hub.combat().pause();
			action.execute();
			CardRepresentation.of(((HasCard) action).card()).startBeingBypassPlayed();
		}
		else if(action instanceof SimpleDrawRequest) {
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
			Vis.handLayer().startEOTToDiscard(card);
		}
		else if(action instanceof RemoveOTFromPlay) {
			Hub.combat().pause();
			action.execute();
			Card card = ((HasCard) action).card();
			CardRepresentation.of(card).startRemoveOT();
		}
		else if(action instanceof SetEnergy || action instanceof ChangeEnergy) {
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
			HasCard hc = ((HasCard) action);
			action.execute();
			Vis.handLayer().startNaturalDiscard(hc.card());
		}
		else if(action instanceof DealDamage || action instanceof ProcrastinatedDamage) {
			HasDamage hd = (HasDamage) action;
			Enemy target = (Enemy) ((TargettedAction) action).target();
			Hub.combat().pause();
			action.execute();
			EnemyRepresentation.of(target).startSlice(hd.damage());
		}
		else if(action instanceof ChangeHealth) {
			ChangeHealth ch = (ChangeHealth) action;
			Hub.combat().pause();
			action.execute();
			Entity target = ch.target();
			if(ch.amount() < 0 && target instanceof Enemy) {
				EnemyRepresentation.of((Enemy) target).startSlice(-ch.amount());
			}
			else {
				updateAllEnemies();
				Vis.ribbonLayer().bottom().update();
				Hub.combat().resume();
			}
		}
		else if(action instanceof DealDamageToAll) {
			HasDamage ddta = (DealDamageToAll) action;
			Hub.combat().pause();
			action.execute();
			List<Enemy> enemies = Hub.enemies();
			for(int i = 0; i < enemies.size() - 1; i++)
				EnemyRepresentation.of(enemies.get(i)).startSlice(ddta.damage(), false);
			EnemyRepresentation.of(enemies.get(enemies.size() - 1)).startSlice(ddta.damage(), true);
		}
		else if(action instanceof UpdateEnemyIntents || action instanceof RemoveModifier ||
				action instanceof ApplyModifier || action instanceof ChangeModifier) {
			waitingOnAnimation = false;
			action.execute();
			updateAllEnemies();
			Vis.ribbonLayer().bottom().updateModifiers();
			updateAllTexts();
		}
		else if(action instanceof CancelIntent) {
			waitingOnAnimation = false;
			action.execute();
			updateAllEnemies();
		}
		else if(action instanceof DecreaseMotivationalVideoEffectiveness || action instanceof IncreaseExcuseCost) {
			waitingOnAnimation = false;
			action.execute();
			updateAllTexts();
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
		else if(action instanceof SOTLoseBlock) {
			waitingOnAnimation = false;
			action.execute();
			Vis.ribbonLayer().bottom().shield().update();
		}
		else if(action instanceof SetInquiry) {
			waitingOnAnimation = false;
			action.execute();
			SetInquiry si = (SetInquiry) action;
//			if(!Hub.combat().requestSupplyCardsToInquiry(Arrays.asList(Hub.hand().get(0))))
//				throw new IllegalStateException("oof");
			Vis.inquiryLayer().startInquiry(si.inquiry());
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
	
	private void updateAllTexts() {
		for(Card c : Hub.combat().cardsInCombat())
			CardRepresentation.of(c).updateText();
	}
	
	@Override
	public void playCardFromHand(Card card, Enemy target) {
		if(Hub.combat().isRunning() || !card.isLegal(target))
			throw new IllegalArgumentException(
			String.format("Can't play. card=%s, target=%s, running=%b", card, target, Hub.combat().isRunning()));
		Hub.combat().stackPlayCardFromHand(card, target);
		Hub.combat().resume();
	}
	
	
	@Override
	public void startCombat(Combat c) {
		c.start();
		Vis.pileLayer().draw().setCards(c.drawPile());
	}

	@Override
	public void update(long diff) {
		GameScene.get().update(diff);
		VisualManager.super.update(diff);
	}
	
	@Override
	public void checkedResumeFromAnimation() {
		if(Hub.combat().isRunning())
			throw new IllegalStateException("Cannot resume; Combat is running");
		waitingOnAnimation = false;
		Hub.combat().resume();
	}
	
	@Override
	public boolean waitingOnAnimation() {
		return waitingOnAnimation;
	}

}
