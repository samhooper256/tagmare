package visuals;

import base.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import visuals.debug.DebugLayer;
import visuals.enemies.EnemyLayer;
import visuals.fxutils.Nodes;
import visuals.hand.HandLayer;
import visuals.info.InfoLayer;
import visuals.inquiry.InquiryLayer;
import visuals.piles.PileLayer;
import visuals.ribbon.RibbonLayer;

/* TODO
 * - if you click a targetted card as it is being drawn from the draw pile (and maybe while it's discarded?) bad stuff
 * happens.
 * - if you hover over a card while they're reorganizing, they get slightly offset in the x direction.
 * - cards need to reorganize as soon as one is put into play. Right now, if you have 9 cards and 1 card in play, drawing
 * a new card doesn't show up to the player (I think). Maybe have a different Group for cards that are in play, that
 * way the same layout calculations can be used for the cards not in play?
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
	private final PileLayer pileLayer;
	private final InquiryLayer inquiryLayer;
	private final HandLayer handLayer;
	private final RibbonLayer ribbonLayer;
	private final DebugLayer debugLayer;
	
	private double mouseX, mouseY;
	
	private GameScene() {
		super(new Pane(), WIDTH, HEIGHT - 20);
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
		pileLayer = new PileLayer();
		inquiryLayer = new InquiryLayer();
		handLayer = new HandLayer();
		ribbonLayer = new RibbonLayer();
		debugLayer = new DebugLayer();
		
		content.getChildren().addAll(bottom, enemyLayer, infoLayer, pileLayer, inquiryLayer,
				handLayer, ribbonLayer, debugLayer);
		
		getStylesheets().add(Main.class.getResource(Main.RESOURCES_PREFIX + "style.css").toExternalForm());
		
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
	
	public PileLayer pileLayer() {
		return pileLayer;
	}
	
	public InquiryLayer inquiryLayer() {
		return inquiryLayer;
	}
	
	public HandLayer handLayer() {
		return handLayer;
	}
	
	public RibbonLayer ribbonLayer() {
		return ribbonLayer;
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
	
	public void debugPrint() {
		System.out.printf("=============================================%n");
		handLayer().debugPrint();
		System.out.printf("=============================================%n");
	}
}