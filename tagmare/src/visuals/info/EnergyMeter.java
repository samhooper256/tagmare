package visuals.info;

import base.temp.Backgrounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Duration;
import visuals.Vis;
import visuals.animations.*;
import visuals.fxutils.Nodes;

public class EnergyMeter extends StackPane {
	
	public static final double WIDTH = 50, HEIGHT = 50;
	
	private static final Duration ENERGY_CHANGE_DURATION = Duration.millis(500);
	
	private class EnergyChangeAnimation extends AbstractAnimation {

		public EnergyChangeAnimation(int dest) {
			super(ENERGY_CHANGE_DURATION);
			bottomText.setText(String.valueOf(dest));
			setFinish(this::finish);
		}
		
		@Override
		public void interpolate(double frac) {
			bottomText.setOpacity(frac);
			topText.setOpacity(1 - frac);
		}
		
		private void finish() {
			String bt = bottomText.getText();
			bottomText.setOpacity(0);
			topText.setOpacity(1);
			topText.setText(bt);
			Vis.manager().checkedResumeFromAnimation();
		}
		
	}
	
	private final Text topText, bottomText;

	public EnergyMeter() {
		setBackground(Backgrounds.of(Color.LIME));
		setMouseTransparent(true);
		Nodes.setPrefAndMaxSize(this, WIDTH, HEIGHT);
		topText = new Text("E");
		bottomText = new Text();
		topText.setTextAlignment(TextAlignment.CENTER);
		bottomText.setTextAlignment(TextAlignment.CENTER);
		getChildren().addAll(bottomText, topText);
	}
	
	public void startEnergyChangeAnimation(int dest) {
		Animation.manager().add(new EnergyChangeAnimation(dest));
	}

}
