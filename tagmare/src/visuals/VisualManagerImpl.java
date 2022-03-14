package visuals;

import java.util.List;

import mechanics.*;
import mechanics.actions.*;
import mechanics.cards.Card;
import mechanics.combat.Combat;
import mechanics.enemies.Enemy;
import visuals.combat.CombatManager;
import visuals.combat.enemies.EnemyRepresentation;
import visuals.combat.ribbon.BottomRibbon;

public final class VisualManagerImpl implements VisualManager {

	private boolean waitingOnAnimation;
	
	//TODO factor out pausing and executing? Need to check on a few things (e.g. why am I explicitly calling resume
	//inside some of the blocks? Should I just return from executeAction(Action) instead of resuming to eliminate
	//the recursive calls to resume()?)
	@Override
	public void executeAction(final Action action) {
//		System.err.printf("executing %s%n", action);
		waitingOnAnimation = true;
		Vis.debugLayer().stackDisplay().update();
		pauseAndExecute(action);
		if(action instanceof PutBypassedCardInPlay) {
			CardRepresentation.of(((HasCard) action).card()).startBeingBypassPlayed();
		}
		else if(action instanceof SimpleDrawRequest) {
			SimpleDrawRequest a = (SimpleDrawRequest) action;
			Card card = a.getCard();
			if(card != null)
				Vis.handLayer().startAddCardToRightAnimation(card);
			else
				pullOut();
		}
		else if(action instanceof EOTDiscard || action instanceof ExplicitDiscard) {
			Card card = ((HasCard) action).card();
			Vis.handLayer().startEOTDiscard(card);
		}
		else if(action instanceof RemoveOTFromPlay) {
			Card card = ((HasCard) action).card();
			CardRepresentation.of(card).startRemoveOT();
		}
		else if(action instanceof ReturnToDrawPile) {
			Card card = ((HasCard) action).card();
			Vis.handLayer().startReturnToDrawPile(card);
		}
		else if(action instanceof SetEnergy || action instanceof ChangeEnergy) {
			Vis.infoLayer().energyMeter().startEnergyChangeAnimation(Hub.energy().amount());
		}
		else if(action instanceof NaturalDiscard) {
			HasCard hc = ((HasCard) action);
			Vis.handLayer().startNaturalDiscard(hc.card());
		}
		else if(action instanceof DealDamage || action instanceof ProcrastinatedDamage) {
			EnemyRepresentation.of(((EnemyTargettedAction) action).target()).startHNBTransition(true);
		}
		else if(action instanceof EnemyBlock || action instanceof EOTEnemyLoseBlock) {
			EnemyRepresentation.of(((EnemyTargettedAction) action).target()).startHNBTransition(true);
		}
		else if(action instanceof DealDamageToAll) {
			List<Enemy> enemies = Hub.enemies();
			for(int i = 0; i < enemies.size() - 1; i++) {
				EnemyRepresentation er = EnemyRepresentation.of(enemies.get(i));
				er.startHNBTransition(false);
			}
			EnemyRepresentation last = EnemyRepresentation.of(enemies.get(enemies.size() - 1));
			last.startHNBTransition(true); //TODO it should resume after whatever animation takes the longest, not on the last one.
		}
		else if(action instanceof ChangeHealth) {
			ChangeHealth ch = (ChangeHealth) action;
			Entity target = ch.target();
			if(ch.amount() < 0 && target instanceof Enemy) {
				EnemyRepresentation er = EnemyRepresentation.of((Enemy) target);
				er.startHNBTransition(true);
			}
			else {
				updateHNBOfAllEnemiesInstantly();
				Vis.ribbonLayer().bottom().update();
				pullOut();
			}
		}
		else if(action instanceof UpdateIntent || action instanceof CancelIntent) {
			EnemyRepresentation.of(((EnemyTargettedAction) action).target()).startIntentTransition();
		}
		else if(action instanceof RefillDrawPile) {
			pullOut();
			Vis.pileLayer().draw().setCards(Hub.drawPile().trueOrder()); //TODO some kind of animation for this?
		}
		else if(action instanceof PlaceCardOnTopOfDrawPile) {
			pullOut();
			Vis.pileLayer().draw().addCardToTop(((HasCard) action).card());
		}
		else if(action instanceof PutCardInPlay) {
			pullOut();
			Vis.handLayer().moveSelectedToInPlay(((HasCard) action).card());
		}
		else if(action instanceof RemoveModifier ||
				action instanceof ApplyModifier || action instanceof ChangeModifier) {
			pullOut();
			updateModifiersOfAllEnemies();
			Vis.ribbonLayer().bottom().updateModifiers();
			updateAllTexts();
		}
		else if(action instanceof DecreaseMotivationalVideoEffectiveness || action instanceof IncreaseExcuseCost) {
			pullOut();
			updateAllTexts();
		}
		else if(action instanceof ClearEnemy) {
			pullOut();
			Vis.enemyLayer().updateEnemiesShown();
		}
		else if(action instanceof TakeDamage || action instanceof GainBlock) {
			pullOut();
			BottomRibbon br = Vis.ribbonLayer().bottom();
			br.healthBar().update();
			br.shield().update();
		}
		else if(action instanceof SOTLoseBlock) {
			pullOut();
			Vis.ribbonLayer().bottom().shield().update();
		}
		else if(action instanceof SetInquiry) {
			pullOut();
			SetInquiry si = (SetInquiry) action;
			Vis.inquiryLayer().startInquiry(si.inquiry());
		}
		else if(action instanceof WinCombat) {
			pullOut();
			Vis.winLayer().startCardReward();
		}
		else {
			pullOut();
		}
	}
	
	/** Used when you've already paused the combat but realize your animation is instantaneous. */
	private void pullOut() {
		waitingOnAnimation = false;
		Hub.combat().setRunning(true);
	}
	
	private void pauseAndExecute(Action action) {
		Hub.combat().pause();
		action.execute();
	}
	
	private void updateHNBOfAllEnemiesInstantly() {
		for(Enemy e : Hub.enemies())
			EnemyRepresentation.of(e).updateHNBInstantly();
	}
	
	private void updateModifiersOfAllEnemies() {
		for(Enemy e : Hub.enemies())
			EnemyRepresentation.of(e).updateModifiers();
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
	public void startNextCombat() {
		GameInstance gi = Hub.instance();
		gi.moveToInCombat();
		Combat c = gi.combat();
		c.startWithoutResuming();
		CombatManager.setupCombat(c);
		Vis.gameScene().showCombat(c);
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
	public void exitWonCombat() {
		Hub.instance().exitWonCombat();
		Vis.gameScene().showCalendar();
	}
	
	@Override
	public boolean waitingOnAnimation() {
		return waitingOnAnimation;
	}

}
