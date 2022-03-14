package visuals.combat.enemies;

import java.util.function.DoubleUnaryOperator;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.Nodes;

/** {@link NumberKnock NumberKnocks} never cause a {@link VisualManager#checkedResumeFromAnimation()}. */
public abstract class NumberKnock extends Group {

	protected class KnockAnimation extends AbstractAnimation {

		public KnockAnimation() {
			super(DURATION);
			setFinish(NumberKnock.this::animationFinished);
		}

		@Override
		public void interpolate(double frac) {
			number.setOpacity(-4 * frac * (frac - 1)); //will go from 0 to 1 to 0, peaking at frac=0.5.
			Nodes.setLayout(number, x0 + x.applyAsDouble(frac), y0 + y.applyAsDouble(frac));
		}
		
	}
	
	public static final Font FONT = Fonts.UI_30_BOLD;
	public static final double WIDTH = 50, HEIGHT = 50;
	public static final Duration DURATION = Duration.millis(500);
	
	private final Label number;
	private final double x0, y0;
	private final DoubleUnaryOperator x, y;
	
	protected KnockAnimation animation;
	
	private int damage;
	
	protected NumberKnock(double x0, double y0, DoubleUnaryOperator x, DoubleUnaryOperator y, Color color) {
		this.x0 = x0;
		this.y0 = y0;
		this.x = x;
		this.y = y;
		number = Nodes.label(FONT, color);
		animation = null;
		Nodes.setLayout(number, x0, y0);
		getChildren().add(number);
	}

	/** Does not do a {@link VisualManager#checkedResumeFromAnimation() checked resume} when finished; removes this
	 * {@link Slice} from its {@link #getParent() parent} when finished. A {@link Slice} can only be played once.
	 * @throws IllegalStateException if has already been played. */
	public void startAnimation() {
		if(animation != null)
			throw new IllegalStateException("Animation has already started");
		animation = new KnockAnimation();
		Animation.manager().add(animation);
	}
	
	protected void animationFinished() {
		((KnockLayer) getParent()).getChildren().remove(this);
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
		number.setText(String.valueOf(damage));
	}
	
	public int damage() {
		return damage;
	}
	
}
