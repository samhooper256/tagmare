package visuals;

import base.Updatable;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import mechanics.Hub;
import visuals.hand.HandLayer;
import visuals.piles.PileLayer;

public class GameScene extends Scene implements Updatable {
	
	public static final double WIDTH = 1920, HEIGHT = 1080;
	
	private static final GameScene INSTANCE = new GameScene();
	
	public static GameScene get() {
		return INSTANCE;
	}
	
	private final Pane content;
	private final Scale scale;
	private final HandLayer handLayer;
	private final PileLayer pileLayer;
	
	private double mouseX, mouseY;
	
	private GameScene() {
		super(new Pane(), WIDTH, HEIGHT);
		scale = new Scale();
		scale.xProperty().bind(widthProperty().divide(WIDTH));
		scale.yProperty().bind(heightProperty().divide(HEIGHT));
		content = new Pane();
		content.getTransforms().add(scale);
		root().getChildren().add(content);
		
		handLayer = new HandLayer();
		pileLayer = new PileLayer();
		content.getChildren().addAll(pileLayer, handLayer);
		pileLayer.draw().setCards(Hub.deck().cards());
		
		setOnMouseMoved(this::mouseMoved);
	}
	
	@Override
	public void update(long diff) {
		for(Node n : content.getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	private void mouseMoved(MouseEvent me) {
		mouseX = me.getX() * WIDTH / getWidth();
		mouseY = me.getY() * HEIGHT / getHeight();
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
	
	public PileLayer pileLayer() {
		return pileLayer;
	}
	
	public double mouseX() {
		return mouseX;
	}
	
	public double mouseY() {
		return mouseY;
	}
	
}