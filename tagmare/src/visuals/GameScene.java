package visuals;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import mechanics.Hub;

public class GameScene extends Scene {
	
	private static final double DEFAULT_WIDTH = 1920, DEFAULT_HEIGHT = 1080;
	private static final GameScene INSTANCE = new GameScene();
	
	public static GameScene get() {
		return INSTANCE;
	}
	
	private final Pane content;
	private final Scale scale;
	
	private GameScene() {
		super(new Pane(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		scale = new Scale();
		scale.xProperty().bind(widthProperty().divide(DEFAULT_WIDTH));
		scale.yProperty().bind(heightProperty().divide(DEFAULT_HEIGHT));
		content = new Pane();
		content.getTransforms().add(scale);
		root().getChildren().add(content);
		
		CardRepresentation cr = CardRepresentation.of(Hub.deck().get(0));
		content().getChildren().add(cr);
	}

	public Pane content() {
		return content;
	}
	
	private Pane root() {
		return (Pane) getRoot();
	}
	
}
