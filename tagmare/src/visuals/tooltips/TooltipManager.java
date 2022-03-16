package visuals.tooltips;

import java.util.*;

import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import utils.Colls;
import visuals.*;
import visuals.fxutils.Nodes;

/** The {@link #column()} will be {@link TooltipColumn#update() updated} after it is laid out but before it is made
 * visible. */
public class TooltipManager {

	private static final Map<Side, List<Side>> PICKING_ORDERS = new HashMap<>();
	
	static {
		PICKING_ORDERS.put(Side.TOP, Colls.ulist(Side.BOTTOM, Side.LEFT, Side.RIGHT));
		PICKING_ORDERS.put(Side.BOTTOM, Colls.ulist(Side.TOP, Side.LEFT, Side.RIGHT));
		PICKING_ORDERS.put(Side.LEFT, Colls.ulist(Side.RIGHT, Side.TOP, Side.BOTTOM));
		PICKING_ORDERS.put(Side.RIGHT, Colls.ulist(Side.LEFT, Side.TOP, Side.BOTTOM));
	}
	
	private static UnsupportedOperationException uoe(Side side) {
		return new UnsupportedOperationException(String.format("Side: %s", side));
	}
	
	private final TooltipColumn column;
	private final Side side;
	private final Region region;
	private final EventHandler<MouseEvent> enterHandler, exitHandler;
	
	private ShowCondition showCondition;
	private boolean installed;

	public TooltipManager(Region region, Side side) {
		this(region, side, null);
	}
	
	public TooltipManager(Region region, Side side, Runnable preDisplayAction) {
		this.column = new TooltipColumn();
		this.region = region;
		this.side = side;
		showCondition = null;
		enterHandler = me -> {
			if(satisfiesShowCondition()) {
				update();
				column().setVisible(true);
			}
		};
		exitHandler = me -> hide();
		installed = false;
	}
	
	private boolean satisfiesShowCondition() {
		return showCondition() == null || showCondition().getAsBoolean();
	}
	
	public void install() {
		if(installed)
			throw new IllegalStateException(String.format("Already installed to region()=%s", region()));
		installed = true;
		Vis.gameScene().tooltipLayer().getChildren().add(column);
		region().addEventHandler(MouseEvent.MOUSE_ENTERED, enterHandler);
		region().addEventHandler(MouseEvent.MOUSE_EXITED, exitHandler);
	}
	
	/** Rearranges this node to fit the {@link #column()} properly. */
	private void update() {
		List<Side> order = PICKING_ORDERS.get(side());
		if(canDisplayInFullOn(side)) {
			layoutInFull(side);
			return;
		}
		for(Side s : order) {
			if(canDisplayInFullOn(s)) {
				layoutInFull(s);
				return;
			}
		}
		layoutPerfectly(side); //we tried all the sides. Just lay it out as if we had infinite space around the region.
		column().update();
	}
	
	//TODO remove this method - I don't THINK we need it.
	private boolean canDisplayPerfectlyOn(Side side) {
		double
			ch = column().getHeight(), cw = column().getWidth(),
			rh = region().getHeight(), rw = region().getWidth(),
			rx = region().getLayoutX(), ry = region().getLayoutY(),
			rcx = rx + .5 * rw, rcy = ry + .5 * rh,
			rrx = rx + rw, rby = ry + rh,
			H = GameScene.HEIGHT, W = GameScene.WIDTH;
		switch(side) {
			case LEFT:
				return rx >= cw && rcy + .5 * ch <= H && rcy - .5 * ch >= 0;
			case RIGHT:
				return rrx + cw <= W && rcy + .5 * ch <= H && rcy - .5 * ch >= 0;
			case TOP:
				return ry >= ch && rcx + .5 * cw <= W && rcx - .5 * cw >= 0;
			case BOTTOM:
				return rby + ch <= H && rcx + .5 * cw <= W && rcx - .5 * cw >= 0;
			default: throw uoe(side);
		}
	}
	
	/** Lays out the {@link #column()} under the assumption that it
	 * {@link #canDisplayPerfectlyOn(Side) can be displayed perfectly} on the given {@link Side}. This method does not
	 * check that condition and can be used without it being true. */
	private void layoutPerfectly(Side side) {
		double
			ch = column().getHeight(), cw = column().getWidth(),
			rh = region().getHeight(), rw = region().getWidth(),
			rx = region().getLayoutX(), ry = region().getLayoutY(),
			rcx = rx + .5 * rw, rcy = ry + .5 * rh,
			rrx = rx + rw, rby = ry + rh;
		switch(side) {
			case LEFT:
				column().setLayoutX(rx - cw);
				column().setLayoutY(rcy - .5 * ch);
				break;
			case RIGHT:
				column().setLayoutX(rrx);
				column().setLayoutY(rcy - .5 * ch);
				break;
			case TOP:
				column().setLayoutX(rcx - .5 * cw);
				column().setLayoutY(ry - ch);
				break;
			case BOTTOM:
				column().setLayoutX(rcx - .5 * cw);
				column().setLayoutY(rby);
				break;
			default: throw uoe(side);
		}
	}
	
	private boolean canDisplayInFullOn(Side side) {
		Bounds rbounds = region().localToScene(region().getBoundsInLocal());
		Bounds cbounds = column().localToScene(column().getBoundsInLocal());
		double
			ch = cbounds.getHeight(), cw = cbounds.getWidth(),
			rh = rbounds.getHeight(), rw = rbounds.getWidth(),
			rx = rbounds.getMinX(), ry = rbounds.getMinY(),
			rrx = rx + rw, rby = ry + rh,
			H = GameScene.get().getHeight(), W = GameScene.get().getWidth();
		switch(side) {
			case LEFT:
				return rx >= cw && ch <= H;
			case RIGHT:
				return rrx + cw <= W && ch <= H;
			case TOP:
				return ry >= ch && cw <= W;
			case BOTTOM:
				return rby + ch <= H && cw <= W;
			default: throw uoe(side); 
		}
	}
	
	/** Assumes the {@link #column()} {@link #canDisplayInFullOn(Side) can be displayed in full} on the given
	 * {@link Side}. <em>Behavior is undefined if this assumption isn't true.</em>*/
	private void layoutInFull(Side side) {
		Bounds rbounds = region().localToScene(region().getBoundsInLocal());
		double
			ch = column().getHeight(), cw = column().getWidth(),
			rh = rbounds.getHeight(), rw = rbounds.getWidth(),
			rx = rbounds.getMinX(), ry = rbounds.getMinY(),
			rcx = rx + .5 * rw, rcy = ry + .5 * rh,
			rrx = rx + rw, rby = ry + rh,
			H = GameScene.HEIGHT, W = GameScene.WIDTH;
		double cy = -1, cx = -1;
		switch(side) {
			case LEFT:
				cx = rx - cw;
				break;
			case RIGHT:
				cx = rrx;
				break;
			case TOP:
				cy = ry - ch;
				break;
			case BOTTOM:
				cy = rby;
				break;
			default: throw uoe(side);
		}
		if(side == Side.LEFT || side == Side.RIGHT) {
			//top and bottom y coordinates for the column. They start as the perfect top (or bottom) y coords.
			cy = rcy - .5 * ch;
			double cby = rcy + .5 * ch;
			if(cy < 0)
				cy = 0; //don't need to update by since we won't use it.
			else if(cby > H)
				cy = H - ch; //""
		}
		else { //we know side is either TOP or BOTTOM since we threw a UOE above if it wasn't L/R/T/B.
			cx = rcx - .5 * cw;
			double crx = rcx + .5 * cw;
			if(cx < 0)
				cx = 0;
			if(crx > W)
				cx = W - cw;
		}
		Nodes.setLayout(column(), inttl(cx, cy));
	}
	
	/** Coordinates in the {@link GameScene#tooltipLayer()}. */
	private static Point2D inttl(double x, double y) {
		return Vis.gameScene().tooltipLayer().sceneToLocal(x, y);
	}
	
	public void hide() {
		column().setVisible(false);
	}
	
	/** The preferred {@link Side}. The {@link #column()} will be displayed on this side if possible. If not possible,
	 * then the picking order (from first to last) for each side is:
	 * <ul>
	 * <li>{@link Side#TOP}: bottom, left, right</li>
	 * <li>{@link Side#BOTTOM}: top, left, right</li>
	 * <li>{@link Side#LEFT}: right, top, bottom</li>
	 * <li>{@link Side#RIGHT}: left, top, bottom</li>
	 * </ul>
	 * For example, if {@link #side()} is {@link Side#TOP TOP} but the tooltip cannot be displayed on the top, it will
	 * be displayed on the bottom. If it cannot be displayed on the bottom either, it will be displayed on the left, and
	 * if it cannot be displayed on the left, it will be displayed on the right.</p>
	 * <p><em>If you reach the last side in the picking order and the {@link TooltipColumn} still cannot be displayed in
	 * full, it will be displayed on the original side (the one returned by this method).</em></p>
	 * */
	public Side side() {
		return side;
	}
	
	public TooltipColumn column() {
		return column;
	}
	
	public Region region() {
		return region;
	}
	
	/** {@code null} (no condition) by default. */
	public ShowCondition showCondition() {
		return showCondition;
	}
	
	public void setShowCondition(ShowCondition showCondition) {
		this.showCondition = showCondition;
	}
	
}
