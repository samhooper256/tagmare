package visuals;

import visuals.calendar.*;
import visuals.combat.CombatEye;
import visuals.combat.debug.DebugLayer;
import visuals.combat.enemies.EnemyLayer;
import visuals.combat.hand.HandLayer;
import visuals.combat.info.InfoLayer;
import visuals.combat.inquiry.InquiryLayer;
import visuals.combat.piles.PileLayer;
import visuals.combat.ribbon.RibbonLayer;
import visuals.combat.win.WinLayer;
import visuals.gallery.Gallery;

public final class Vis {
	
	private Vis() {
		
	}
	
	public static VisualManager manager() {
		return VisualManager.get();
	}

	public static GameScene gameScene() {
		return GameScene.get();
	}
	
	public static CalendarEye calendarEye() {
		return gameScene().calendarEye();
	}
	
	public static CalendarLayer calendarLayer() {
		return gameScene().calendarLayer();
	}
	
	public static HandLayer handLayer() {
		return gameScene().handLayer();
	}
	
	public static RibbonLayer ribbonLayer() {
		return gameScene().ribbonLayer();
	}
	
	public static PileLayer pileLayer() {
		return gameScene().pileLayer();
	}

	public static WinLayer winLayer() {
		return gameScene().winLayer();
	}
	
	public static DebugLayer debugLayer() {
		return gameScene().debugLayer();
	}
	
	public static InfoLayer infoLayer() {
		return gameScene().infoLayer();
	}
	
	public static EnemyLayer enemyLayer() {
		return gameScene().enemyLayer();
	}
	
	public static InquiryLayer inquiryLayer() {
		return gameScene().inquiryLayer();
	}
	
	public static boolean inquiryActive() {
		return inquiryLayer().isActive();
	}
	
	public static CombatEye combatEye() {
		return gameScene().combatEye();
	}
	
	public static Gallery deckGallery() {
		return gameScene().deckGallery();
	}
	
	public static Gallery drawPileGallery() {
		return gameScene().drawPileGallery();
	}
	
	public static Gallery discardPileGallery() {
		return gameScene().discardPileGallery();
	}
	
	public static double mouseX() {
		return gameScene().mouseX();
	}
	
	public static double mouseY() {
		return gameScene().mouseY();
	}
	
}
