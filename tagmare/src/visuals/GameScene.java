package visuals;

import base.Updatable;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import visuals.hand.HandLayer;

public class GameScene extends Scene implements Updatable {
	
	public static final double WIDTH = 1920, HEIGHT = 1080;
	
	private static final GameScene INSTANCE = new GameScene();
	
	public static GameScene get() {
		return INSTANCE;
	}
	
	private final Pane content;
	private final Scale scale;
	private final HandLayer handLayer;
	
	private GameScene() {
		super(new Pane(), WIDTH, HEIGHT);
		scale = new Scale();
		scale.xProperty().bind(widthProperty().divide(WIDTH));
		scale.yProperty().bind(heightProperty().divide(HEIGHT));
		content = new Pane();
		content.getTransforms().add(scale);
		root().getChildren().add(content);
		
		handLayer = new HandLayer();
		content.getChildren().addAll(handLayer);
	}
	
	@Override
	public void update(long diff) {
		for(Node n : content.getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	private Pane root() {
		return (Pane) getRoot();
	}

	public Pane content() {
		return content;
	}
	
	public HandLayer handLayer() {
		return handLayer;
	}
	
}