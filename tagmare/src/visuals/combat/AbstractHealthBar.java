package visuals.combat;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import mechanics.*;
import utils.Nums;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.*;

public class AbstractHealthBar extends Pane {

	public static final Color TEXT_COLOR = Color.WHITE;
	public static final double DEFAULT_SIDE_PADDING = 0, DEFUALT_TEXT_Y_OFFSET = 0;
	
	private static final Duration SLIDE_DURATION = Duration.millis(500);
	
	private class HPSlide extends AbstractAnimation {

		private final boolean resume;
		
		private double startWidth, destWidth;
		
		public HPSlide(boolean resume) {
			super(SLIDE_DURATION, Interpolator.SQRT);
			this.resume = resume;
			destWidth = health().proportion() * width();
			startWidth = filled.getWidth();
			setFinish(this::finisher);
		}

		@Override
		public void interpolate(double frac) {
			Nodes.setPrefAndMaxWidth(filled, Nums.lerp(frac, startWidth, destWidth));
		}
		
		private void finisher() {
			if(resume)
				Vis.manager().checkedResumeFromAnimation();
		}
		
	}

	private final double width, height, roundingRadius, sidePadding, textYOffset;
	private final Font font;
	private final Health health;
	private final Region filled, back;
	private final Label hp, max;
	
	public AbstractHealthBar(double width, double height, double roundingRadius, Font font, Health health) {
		this(width, height, roundingRadius, DEFAULT_SIDE_PADDING, font, health);
	}
	
	public AbstractHealthBar(double width, double height, double roundingRadius, double sidePadding,
			Font font, Health health) {
		this(width, height, roundingRadius, sidePadding, DEFUALT_TEXT_Y_OFFSET, font, health);
	}
	
	public AbstractHealthBar(double width, double height, double roundingRadius, double sidePadding,
			double textYOffset, Font font, Health health) {
		this.width = width;
		this.height = height;
		this.roundingRadius = roundingRadius;
		this.sidePadding = sidePadding;
		this.textYOffset = textYOffset;
		this.font = font;
		this.health = health;
		Nodes.setPrefAndMaxSize(this, width, height);
		filled = new StackPane();
		filled.setBackground(Backgrounds.rounded(Colors.HEALTH_FILLED, roundingRadius));
		Nodes.setPrefAndMaxSize(filled, width, height);
		back = new StackPane();
		back.setBackground(Backgrounds.rounded(Colors.HEALTH_EMPTY, roundingRadius));
		Nodes.setPrefAndMaxSize(back, width, height);
		hp = createHP();
		max = createMax();
		getChildren().addAll(back, filled, hp, max);
	}
	
	private Label createHP() {
		Label hp = Nodes.label(String.valueOf(health().hp()), font(), TEXT_COLOR);
		hp.layoutYProperty().bind(hp.heightProperty().multiply(-.5).add(height() * .5 + textYOffset()));
		hp.setLayoutX(sidePadding());
		return hp;
	}
	
	private Label createMax() {
		Label max = Nodes.label(String.valueOf(health().hp()), font(), TEXT_COLOR);
		max.layoutYProperty().bind(max.heightProperty().multiply(-.5).add(height() * .5 + textYOffset()));
		max.layoutXProperty().bind(max.widthProperty().multiply(-1).add(width() - sidePadding()));
		return max;
	}
	
	public void update() {
		hp.setText(String.valueOf(health().hp()));
		max.setText(String.valueOf(health().max()));
	}
	
	public void startTransition(boolean resume) {
		update();
		Animation.manager().add(new HPSlide(resume));
	}
	
	/** The change in the HP value showing vs. the actual HP value. (actual - showing)*/
	public int hpChange() {
		return health().hp() - hpShowing();
	}
	
	public boolean hasChanged() {
		return hpChange() != 0;
	}
	
	public int hpShowing() {
		return Integer.parseInt(hp.getText());
	}

	public double secToAnimateChange() {
		return hasChanged() ? SLIDE_DURATION.toSeconds() : 0;
	}
	
	public double width() {
		return width;
	}
	
	public double height() {
		return height;
	}
	
	public double roundingRadius() {
		return roundingRadius;
	}
	
	public double sidePadding() {
		return sidePadding;
	}
	
	public double textYOffset() {
		return textYOffset;
	}
	
	public Font font() {
		return font;
	}
	
	public Health health() {
		return health;
	}
	
	public Region filled() {
		return filled;
	}
	
	public Region back() {
		return back;
	}
	
}
