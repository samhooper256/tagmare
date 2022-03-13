package visuals.combat.enemies;

import java.util.function.DoubleUnaryOperator;

import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.Nodes;

public class Slice extends Group {

	public static final Font FONT = Fonts.UI_30_BOLD;
	public static final Color COLOR = Color.RED;
	public static final double WIDTH = 50, HEIGHT = 50;
	
	private static final Duration DURATION = Duration.millis(500);
	private static final double NX0 = 20, NY0 = 0;
	private static final DoubleUnaryOperator X = t -> NX0 + 20 * t, Y = t -> NY0 + 32 * t * (t - .9);
	
	private class SliceAnimation extends AbstractAnimation {

		public SliceAnimation() {
			super(DURATION);
			setFinish(Slice.this::sliceFinished);
		}

		@Override
		public void interpolate(double frac) {
			number.setOpacity(-4 * frac * (frac - 1)); //will go from 0 to 1 to 0, peaking at frac=0.5.
			Nodes.setLayout(number, X.applyAsDouble(frac), Y.applyAsDouble(frac));
		}
		
	}
	
	
	private final Label number;
	private SliceAnimation animation;
	
	private int damage;
	private boolean checkedResume;
	
	public Slice(int damage) {
		number = Nodes.label(FONT, COLOR);
		checkedResume = true;
		Nodes.setLayout(number, NX0, NY0);
		setDamage(damage);
		getChildren().add(number);
	}

	/** Calls a {@link VisualManager#checkedResumeFromAnimation() checked resume} and removes this {@link Slice} from
	 * its {@link #getParent() parent} when finished. A {@link Slice} can only be played once.
	 * @throws IllegalStateException if has already been played. */
	public void startAnimation(boolean checkedResume) {
		if(animation != null)
			throw new IllegalStateException("Animation has already started");
		this.checkedResume = checkedResume;
		animation = new SliceAnimation();
		Animation.manager().add(animation);
	}
	
	private void sliceFinished() {
		Parent p = getParent();
		final ObservableList<Node> children;
		if(p instanceof Group)
			children = ((Group) p).getChildren();
		else if(p instanceof Pane)
			children = ((Pane) p).getChildren();
		else
			throw new IllegalArgumentException(String.format(
				"Cannot remove this Slice from its parent. slice=%s, parent=%s", this, p));
		children.remove(this);
		if(checkedResume)
			Vis.manager().checkedResumeFromAnimation();
	}

	public void setDamage(int damage) {
		this.damage = damage;
		number.setText(String.valueOf(damage));
	}
	
	public int damage() {
		return damage;
	}
	
}
