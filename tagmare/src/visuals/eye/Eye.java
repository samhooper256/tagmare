package visuals.eye;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import utils.Nums;
import visuals.GameScene;
import visuals.animations.*;

public class Eye extends Pane {

	private static final Duration DURATION = Duration.seconds(1);
	
	private class Close extends AbstractAnimation {

		public Close(Runnable finisher) {
			super(DURATION);
			setFinish(finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			if(frac >= .99)
				midBlocker.setVisible(true);
			top.arcYProperty().set(Nums.lerp(frac, TopEyelid.OPEN_ARC_Y, TopEyelid.CLOSED_ARC_Y));
			bottom.arcYProperty().set(Nums.lerp(frac, BottomEyelid.OPEN_ARC_Y, BottomEyelid.CLOSED_ARC_Y));
		}
		
	}
	
	private class Open extends AbstractAnimation {

		public Open() {
			super(DURATION);
			setFinish(this::finisher);
		}
		
		@Override
		public void interpolate(double frac) {
			top.arcYProperty().set(Nums.lerp(frac, TopEyelid.CLOSED_ARC_Y, TopEyelid.OPEN_ARC_Y));
			bottom.arcYProperty().set(Nums.lerp(frac, BottomEyelid.CLOSED_ARC_Y, BottomEyelid.OPEN_ARC_Y));
		}
		
		private void finisher() {
			if(endAction != null)
				endAction.run();
			setEndAction(null);
			setPickOnBounds(false);
		}
		
	}
	
	public static final double
		SIDE_DISTANCE = 400,
		LEFT_X = -SIDE_DISTANCE, RIGHT_X = GameScene.WIDTH + SIDE_DISTANCE,
		Y = GameScene.CENTER_Y,
		RADIUS_X = GameScene.CENTER_X - LEFT_X;
	
	private final TopEyelid top;
	private final BottomEyelid bottom;
	private final Rectangle midBlocker;
	
	private boolean inProgress;
	private Runnable endAction;
	
	public Eye() {
		setPickOnBounds(false);
		top = new TopEyelid();
		bottom = new BottomEyelid();
		midBlocker = new Rectangle(0, Y - 20, GameScene.WIDTH, 40);
		midBlocker.setVisible(false);
		getChildren().addAll(top, bottom, midBlocker);
	}

	public void startTransition(Runnable peakAction) {
		startTransition(peakAction, null);
	}
	
	/** The {@code peakAction} is responsible for calling {@link #proceedInTransition()}. {@code endAction} will be
	 * run once the eyes are fully open. Note that the {@code endAction} can also be set by a sublcass during the
	 * {@code peakAction} using {@link #setEndAction(Runnable)}. */
	public void startTransition(Runnable peakAction, Runnable endAction) {
		inProgress = true;
		this.endAction = endAction;
		setPickOnBounds(true);
		Animation.manager().add(new Close(peakAction));
	}
	
	public void proceedInTransition() {
		if(!inProgress)
			throw new IllegalStateException("This Eye is not in progress.");
		midBlocker.setVisible(false);
		Animation.manager().add(new Open());
	}
	
	protected void setEndAction(Runnable endAction) {
		this.endAction = endAction;
	}
	
	public TopEyelid top() {
		return top;
	}
	
}
