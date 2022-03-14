package visuals.combat.enemies;

import java.util.WeakHashMap;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import mechanics.Block;
import mechanics.enemies.Enemy;
import mechanics.modifiers.Modifier;
import visuals.*;
import visuals.fxutils.*;

public class EnemyRepresentation extends StackPane {

	private static final WeakHashMap<Enemy, EnemyRepresentation> MAP = new WeakHashMap<>();
	
	public static EnemyRepresentation of(Enemy enemy) {
		if(!MAP.containsKey(enemy))
			MAP.put(enemy, new EnemyRepresentation(enemy));
		return MAP.get(enemy);
	}
	
	private final Enemy enemy;
	private final VBox vBox, modifierBox;
	private final Text name, healthAndBlock, modifierLabel;
	private final Sprite sprite;
	private final Pane sliceLayer;
	
	private final IntentContainer intentContainer;
	
	private EnemyRepresentation(Enemy enemy) {
		this.enemy = enemy;
		name = new Text(enemy.name());
		name.setFont(Fonts.UI_14);
		healthAndBlock = new Text(getHealthAndBlockString());
		healthAndBlock.setFont(Fonts.UI_14);
		intentContainer = new IntentContainer(enemy.intent());
		modifierLabel = new Text("Modifiers:");
		modifierLabel.setFont(Fonts.UI_14);
		modifierBox = new VBox(modifierLabel);
		modifierBox.setAlignment(Pos.TOP_CENTER);
		sprite = new Sprite(Images.TEST_ENEMY);
		vBox = new VBox(intentContainer, name, sprite, healthAndBlock, modifierLabel, modifierBox);
		vBox.setAlignment(Pos.CENTER);
		sliceLayer = new Pane();
		getChildren().addAll(vBox, sliceLayer);
		Nodes.setPrefAndMaxWidth(this, 300);
		setOnMouseClicked(me -> mouseClicked());
	}

	private String getHealthAndBlockString() {
		Block block = enemy().block();
		return String.format("%d/%d%s", enemy().health().hp(), enemy.health().max(), block.isZero() ? "" : 
			String.format("(%d block)", block.amount()));
	}
	
	private void mouseClicked() {
		if(Vis.handLayer().hasSelected())
			Vis.handLayer().selected().requestStartBeingPlayed(enemy());
	}
	
	public void updateHealthAndBlockAndModifiers() {
		updateHealthAndBlock();
		updateModifiers();
	}
	
	private void updateHealthAndBlock() {
		healthAndBlock.setText(getHealthAndBlockString());
	}

	private void updateModifiers() {
		ObservableList<Node> children = modifierBox.getChildren();
		children.clear();
		for(Modifier m : enemy().modifiers()) {
			Text t = new Text(m.toString());
			t.setFont(Fonts.UI_14);
			children.add(t);
		}
	}
	
	public void startIntentTransition() {
		intentContainer.startTransition(enemy.intent());
	}
	
	public void startSlice(int damage) {
		startSlice(damage, true);
	}
	
	public void startSlice(int damage, boolean checkedResume) {
		updateHealthAndBlock();
		sliceLayer.getChildren().clear();
		Slice slice = new Slice(damage);
		Nodes.setLayout(slice, getMaxWidth() * .5 - Slice.WIDTH * .5, getHeight() * .5 - Slice.HEIGHT * .5);
		sliceLayer.getChildren().add(slice);
		slice.startAnimation(checkedResume);
	}
	
	public Enemy enemy() {
		return enemy;
	}
	
}
