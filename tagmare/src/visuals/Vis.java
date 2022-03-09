package visuals;

import base.VisualManager;
import visuals.debug.DebugLayer;
import visuals.enemies.EnemyLayer;
import visuals.hand.HandLayer;
import visuals.info.InfoLayer;
import visuals.inquiry.InquiryLayer;
import visuals.piles.PileLayer;
import visuals.ribbon.RibbonLayer;

public final class Vis {
	
	private Vis() {
		
	}
	
	public static VisualManager manager() {
		return VisualManager.get();
	}

	public static GameScene gameScene() {
		return GameScene.get();
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
	
	public static double mouseX() {
		return gameScene().mouseX();
	}
	
	public static double mouseY() {
		return gameScene().mouseY();
	}
	
}
