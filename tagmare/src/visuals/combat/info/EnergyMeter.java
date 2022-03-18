package visuals.combat.info;

import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.util.Duration;
import visuals.*;
import visuals.animations.*;
import visuals.fxutils.*;

public class EnergyMeter extends StackPane {
	
	public static final double WIDTH = 70, HEIGHT = 70;
	
	private static final Duration ENERGY_CHANGE_DURATION = Duration.millis(500);
	private static final Font FONT = Fonts.UI_72_BOLD;
	
	private class EnergyChangeAnimation extends AbstractAnimation {

		private final int dest;
		
		public EnergyChangeAnimation(int dest) {
			super(ENERGY_CHANGE_DURATION);
			this.dest = dest;
			bottomText.setText(String.valueOf(dest));
			setFinish(this::finish);
		}
		
		@Override
		public void interpolate(double frac) {
			bottomText.setOpacity(frac);
			topText.setOpacity(1 - frac);
		}
		
		private void finish() {
			setEnergy(dest);
			Vis.manager().checkedResumeFromAnimation();
		}
		
	}
	
	private final Text topText, bottomText;

	public EnergyMeter() {
		setBackground(Backgrounds.of(Colors.CARD_BACKGROUND));
		setMouseTransparent(true);
		Nodes.setAllSizes(this, WIDTH, HEIGHT);
		topText = Nodes.text("E", FONT);
		bottomText = Nodes.text(FONT);
		topText.setTextAlignment(TextAlignment.CENTER);
		bottomText.setTextAlignment(TextAlignment.CENTER);
		getChildren().addAll(bottomText, topText);
	}
	
	public void startEnergyChangeAnimation(int dest) {
		Animation.manager().add(new EnergyChangeAnimation(dest));
	}
	
	public void setEnergy(int energy) {
		topText.setText(String.valueOf(energy));
		topText.setOpacity(1);
		bottomText.setText("");
		bottomText.setOpacity(0);
	}
	
}
