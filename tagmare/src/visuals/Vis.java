package visuals;

import base.VisualManager;
import visuals.debug.DebugLayer;
import visuals.hand.HandLayer;
import visuals.piles.PileLayer;

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
	
	public static PileLayer pileLayer() {
		return gameScene().pileLayer();
	}
	
	public static DebugLayer debugLayer() {
		return gameScene().debugLayer();
	}
	
	public static double mouseX() {
		return gameScene().mouseX();
	}
	
	public static double mouseY() {
		return gameScene().mouseY();
	}
	
}
