package visuals;

import visuals.hand.HandLayer;

public final class Vis {
	
	private Vis() {
		
	}

	public static GameScene gameScene() {
		return GameScene.get();
	}
	
	public static HandLayer handLayer() {
		return gameScene().handLayer();
	}
	
}
