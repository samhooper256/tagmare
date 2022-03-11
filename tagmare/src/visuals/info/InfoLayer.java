package visuals.info;

import base.Updatable;
import javafx.scene.layout.Pane;
import visuals.fxutils.Nodes;

public class InfoLayer extends Pane implements Updatable {

	private final EndTurnButton endTurnButton;
	private final EnergyMeter energyMeter;
	
	public InfoLayer() {
		setPickOnBounds(false);
		endTurnButton = new EndTurnButton();
		Nodes.setLayout(endTurnButton, 1600, 650);
		energyMeter = new EnergyMeter();
		Nodes.setLayout(energyMeter, 100, 800);
		getChildren().addAll(endTurnButton, energyMeter);
	}
	
	@Override
	public void update(long diff) {
		endTurnButton.update(diff);
	}
	
	public EndTurnButton endTurnButton() {
		return endTurnButton;
	}
	
	public EnergyMeter energyMeter() {
		return energyMeter;
	}
	
}
