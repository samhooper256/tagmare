package visuals;

import java.util.*;

import base.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import mechanics.combat.Combat;
import visuals.calendar.*;
import visuals.calendar.bottomribbon.BottomRibbonLayer;
import visuals.calendar.topribbon.TopRibbonLayer;
import visuals.combat.*;
import visuals.combat.debug.DebugLayer;
import visuals.combat.enemies.EnemyLayer;
import visuals.combat.hand.HandLayer;
import visuals.combat.info.InfoLayer;
import visuals.combat.inquiry.InquiryLayer;
import visuals.combat.piles.PileLayer;
import visuals.combat.ribbon.*;
import visuals.combat.win.WinLayer;
import visuals.fxutils.Nodes;
import visuals.gallery.Gallery;

public class GameScene extends Scene implements Updatable {
	
	public static final double WIDTH = 1920, HEIGHT = 1080, CENTER_X = WIDTH * .5, CENTER_Y = HEIGHT * .5;
	
	private static final GameScene INSTANCE = new GameScene();
	
	public static GameScene get() {
		return INSTANCE;
	}
	
	private final Pane content, lowerContent, galleryLayer, tooltipLayer, eyeLayer;
	private final Scale scale;

	//Galleries:
	private final Gallery deckGallery, drawPileGallery, discardPileGallery;
	private final ForesightGallery foresightGallery;
	
	//Calendar:
	private final CalendarLayer calendarLayer;
	private final BottomRibbonLayer bottomRibbonLayer;
	private final TopRibbonLayer topRibbonLayer;
	private final CalendarEye calendarEye;
	
	//Combat:
	private final Pane bottom;
	private final EnemyLayer enemyLayer;
	private final InfoLayer infoLayer;
	private final PileLayer pileLayer;
	private final InquiryLayer inquiryLayer;
	private final HandLayer handLayer;
	private final RibbonLayer ribbonLayer;
	private final WinLayer winLayer;
	private final DebugLayer debugLayer;
	private final CombatEye combatEye;
	
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
		winLayer = new WinLayer();
		debugLayer = new DebugLayer();
		
		combatChildren = new ArrayList<>();
		Collections.addAll(combatChildren, bottom, enemyLayer, infoLayer, pileLayer, inquiryLayer,
				handLayer, ribbonLayer, winLayer, debugLayer);
		
		lowerContent = new Pane();
		lowerContent.getChildren().addAll(calendarChildren);
		
		deckGallery = new Gallery("Deck");
		drawPileGallery = new Gallery("Draw Pile", "Cards are not shown in the order they will be drawn.");
		discardPileGallery = new Gallery("Discard Pile", "Cards are shown in the order they were discarded.");
		foresightGallery = new ForesightGallery();
		galleryLayer = new Pane(deckGallery, drawPileGallery, discardPileGallery, foresightGallery);
		galleryLayer.setPickOnBounds(false);

		tooltipLayer = new Pane();
		tooltipLayer.setMouseTransparent(true);
		
		calendarEye = new CalendarEye();
		combatEye = new CombatEye();
		eyeLayer = new Pane(calendarEye, combatEye);
		eyeLayer.setPickOnBounds(false);
		
		content.getChildren().addAll(lowerContent, galleryLayer, tooltipLayer, eyeLayer);
		
		getStylesheets().add(Main.class.getResource(Main.RESOURCES_PREFIX + "style.css").toExternalForm());
		
		setOnMouseMoved(this::mouseMoved);
		
		setOnKeyPressed(ke -> {
			if(ke.getCode() == KeyCode.D)
				Main.debugPrint();
		});
	}
	
	@Override
	public void update(long diff) {
		for(Node n : lowerContent.getChildren())
			if(n instanceof Updatable)
				((Updatable) n).update(diff);
	}
	
	private void mouseMoved(MouseEvent me) {
		mouseX = me.getX() * WIDTH / getWidth();
		mouseY = me.getY() * HEIGHT / getHeight();
	}
	
	public void showCombat(Combat c) {
		lowerContent.getChildren().clear();
		lowerContent.getChildren().addAll(combatChildren);
	}
	
	public void showCalendar() {
		lowerContent.getChildren().clear();
		lowerContent.getChildren().addAll(calendarChildren);
		bottomRibbonLayer.continueButton().reEnable(); //TODO this should be in a method called CalendarManager.setupCalendar(...)?
	}
	
	private Pane root() {
		return (Pane) getRoot();
	}
	
	public CalendarEye calendarEye() {
		return calendarEye;
	}
	
	public CalendarLayer calendarLayer() {
		return calendarLayer;
	}
	
	public EnemyLayer enemyLayer() {
		return enemyLayer;
	}
	
	public InfoLayer infoLayer() {
		return infoLayer;
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
	
	public WinLayer winLayer() {
		return winLayer;
	}
	
	public DebugLayer debugLayer() {
		return debugLayer;
	}
	
	public CombatEye combatEye() {
		return combatEye;
	}
	
	public Gallery deckGallery() {
		return deckGallery;
	}
	
	public Gallery drawPileGallery() {
		return drawPileGallery;
	}
	
	public Gallery discardPileGallery() {
		return discardPileGallery;
	}
	
	public ForesightGallery foresightGallery() {
		return foresightGallery;
	}
	
	public Pane tooltipLayer() {
		return tooltipLayer;
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
		ribbonLayer().debugPrint();
		System.out.printf("=============================================%n");
	}
	
}