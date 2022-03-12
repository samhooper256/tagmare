package visuals.calendar;

import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.Nums;
import visuals.*;
import visuals.calendar.bottomribbon.BottomRibbonLayer;
import visuals.calendar.topribbon.TopRibbonLayer;
import visuals.fxutils.*;

/** Contains the paper and the desk behind it. */
public class CalendarLayer extends Pane {

	/** The width of the paper calendar. */
	public static final double WIDTH = Images.CALENDAR.getWidth();
	
	/** The y-coordinate of the topmost visible part of the paper calendar. */
	public static final double Y = TopRibbonLayer.HEIGHT;
	
	/** The y-coordinate of the paper. */
	public static final double X = GameScene.CENTER_X - WIDTH * .5;
	
	/** The height of the paper calendar. */
	public static final double HEIGHT = Images.CALENDAR.getHeight();

	private static final double TEXT_X = X + 3 * 96 + 10;
	private static final double WINDOW_HEIGHT = GameScene.HEIGHT - TopRibbonLayer.HEIGHT - BottomRibbonLayer.HEIGHT;
	private static final double SCROLL_MULTIPLIER = 4;
	private static final double WEEK_SPACING = .38 * 3 * 96;
	private static final double WEEK_1_Y = Y + 100;
	
	private final Sprite calendar, desk;
	private final Group content;
	
	public CalendarLayer() {
		calendar = new Sprite(Images.CALENDAR);
		desk = new Sprite(Images.DESK);
		calendar.setLayoutX(X);
		content = new Group(desk, calendar);
		content.setLayoutY(Y);
		for(int i = 0; i < 30; i++) {
			Text l = new Text(String.valueOf(i + "weeeeE"));
			Nodes.setLayout(l, TEXT_X, WEEK_1_Y + WEEK_SPACING * i);
			content.getChildren().add(l);
		}
		getChildren().addAll(content);
		setOnScroll(this::scroll);
	}

	private void scroll(ScrollEvent se) {
		content.setLayoutY(
				Nums.clamp(content.getLayoutY() + se.getDeltaY() * SCROLL_MULTIPLIER, Y - HEIGHT + WINDOW_HEIGHT, Y));
	}
	
	/** The offset from {@link #Y}. */
	public double yOffset() {
		return content.getLayoutY();
	}
	
}
