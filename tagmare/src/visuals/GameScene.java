package visuals;

import base.Updatable;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import mechanics.Hub;
import visuals.debug.DebugLayer;
import visuals.enemies.EnemyLayer;
import visuals.fxutils.Nodes;
import visuals.hand.HandLayer;
import visuals.info.InfoLayer;
import visuals.piles.PileLayer;

/* TODO
 * - if you click a targetted card and then click end turn
 */
public class GameScene extends Scene implements Updatable {
	
	public static final double WIDTH = 1920, HEIGHT = 1080, CENTER_X = WIDTH * .5, CENTER_Y = HEIGHT * .5;
	
	private static final GameScene INSTANCE = new GameScene();
	
	public static GameScene get() {
		return INSTANCE;
	}
	
	private final Pane content;
	private final Scale scale;
	private final Pane bottom;
	private final EnemyLayer enemyLayer;
	private final InfoLayer infoLayer;
	private final HandLayer handLayer;
	private final PileLayer pileLayer;
	private final DebugLayer debugLayer;
	
	private double mouseX, mouseY;
	
	private GameScene() {
		super(new Pane(), WIDTH, HEIGHT);
		scale = new Scale();
		scale.xProperty().bind(widthProperty().divide(WIDTH));
		scale.yProperty().bind(heightProperty().divide(HEIGHT));
		content = new Pane();
		content.getTransforms().add(scale);
		root().getChildren().add(content);
		
		bottom = new Pane();
		Nodes.setMinSize(bottom, Double.MAX_VALUE, Double.MAX_VALUE);
		bottom.setPickOnBounds(true);
		bottom.setOnMousePressed(eh -> Vis.handLayer().notifyClickedInDeadSpace());
		enemyLayer = new EnemyLayer();
		infoLayer = new InfoLayer();
		handLayer = new HandLayer();
		pileLayer = new PileLayer();
		debugLayer = new DebugLayer();
		
		content.getChildren().addAll(bottom, enemyLayer, infoLayer, pileLayer, handLayer, debugLayer);
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
	
	public EnemyLayer enemyLayer() {
		return enemyLayer;
	}
	
	public HandLayer handLayer() {
		return handLayer;
	}
	
	public PileLayer pileLayer() {
		return pileLayer;
	}
	
	public DebugLayer debugLayer() {
		return debugLayer;
	}
	
	public InfoLayer infoLayer() {
		return infoLayer;
	}
	
	public double mouseX() {
		return mouseX;
	}
	
	public double mouseY() {
		return mouseY;
	}
	
}