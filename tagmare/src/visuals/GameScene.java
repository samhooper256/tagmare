package visuals;

import java.util.*;

import base.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import mechanics.Combat;
import visuals.calendar.CalendarLayer;
import visuals.calendar.bottomribbon.BottomRibbonLayer;
import visuals.calendar.topribbon.TopRibbonLayer;
import visuals.combat.debug.DebugLayer;
import visuals.combat.enemies.EnemyLayer;
import visuals.combat.hand.HandLayer;
import visuals.combat.info.InfoLayer;
import visuals.combat.inquiry.InquiryLayer;
import visuals.combat.piles.PileLayer;
import visuals.combat.ribbon.*;
import visuals.fxutils.Nodes;

/* TODO
 * - if you click a targetted card as it is being drawn from the draw pile (and maybe while it's discarded?) bad stuff
 * happens.
 * - if you hover over a card while they're reorganizing, they get slightly offset in the x direction.
 */
public class GameScene extends Scene implements Updatable {
	
	public static final double WIDTH = 1920, HEIGHT = 1080, CENTER_X = WIDTH * .5, CENTER_Y = HEIGHT * .5;
	
	private static final GameScene INSTANCE = new GameScene();
	
	public static GameScene get() {
		return INSTANCE;
	}
	
	private final Pane content;
	private final Scale scale;
	
	//Calendar:
	private final CalendarLayer calendarLayer;
	private final BottomRibbonLayer bottomRibbonLayer;
	private final TopRibbonLayer topRibbonLayer;
	
	//Combat:
	private final Pane bottom;
	private final EnemyLayer enemyLayer;
	private final InfoLayer infoLayer;
	private final PileLayer pileLayer;
	private final InquiryLayer inquiryLayer;
	private final HandLayer handLayer;
	private final RibbonLayer ribbonLayer;
	private final DebugLayer debugLayer;
	
	private final List<Node> calendarChildren, combatChildren;
	
	private double mouseX, mouseY;
	
	private GameScene() {
		super(new Pane(), WIDTH, HEIGHT - 20);
		scale = new Scale();
		scale.xProperty().bind(widthProperty().divide(WIDTH));
		scale.yProperty().bind(heightProperty().divide(HEIGHT));
		content = new Pane();
		content.getTransforms().add(scale);
		root().getChildren().add(content);
		
		calendarLayer = new CalendarLayer();
		bottomRibbonLayer = new BottomRibbonLayer();
		topRibbonLayer = new TopRibbonLayer();
		calendarChildren = new ArrayList<>();
		Collections.addAll(calendarChildren, calendarLayer, topRibbonLayer, bottomRibbonLayer);
		
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
		
		combatChildren = new ArrayList<>();
		Collections.addAll(combatChildren, bottom, enemyLayer, infoLayer, pileLayer, inquiryLayer,
				handLayer, ribbonLayer, debugLayer);
		
		content.getChildren().addAll(calendarChildren);
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
	
	public void showCombat(Combat c) {
		content.getChildren().clear();
		content.getChildren().addAll(combatChildren);
		pileLayer().draw().setCards(c.drawPile());
		enemyLayer().setupEnemies();
	}
	
	private Pane root() {
		return (Pane) getRoot();
	}

	public Pane content() {
		return content;
	}
	
	public CalendarLayer calendarLayer() {
		return calendarLayer;
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
		System.out.printf("===GameScene=================================%n");
		handLayer().debugPrint();
		System.out.printf("=============================================%n");
	}
	
}