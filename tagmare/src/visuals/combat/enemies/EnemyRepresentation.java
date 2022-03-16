package visuals.combat.enemies;

import java.util.WeakHashMap;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import mechanics.enemies.Enemy;
import mechanics.modifiers.Modifier;
import visuals.*;
import visuals.fxutils.*;

/** HNB means {@link HealthAndBlock}. */
public class EnemyRepresentation extends StackPane {

	private static final WeakHashMap<Enemy, EnemyRepresentation> MAP = new WeakHashMap<>();
	
	public static EnemyRepresentation of(Enemy enemy) {
		if(!MAP.containsKey(enemy))
			MAP.put(enemy, new EnemyRepresentation(enemy));
		return MAP.get(enemy);
	}
	
	private final Enemy enemy;
	private final VBox vBox, modifierBox;
	private final Text name, modifierLabel;
	private final HealthAndBlock healthAndBlock;
	private final Sprite sprite;
	private final KnockLayer sliceLayer, chipLayer;
	
	private final IntentContainer intentContainer;
	
	private EnemyRepresentation(Enemy enemy) {
		this.enemy = enemy;
		name = new Text(enemy.displayName());
		name.setFont(Fonts.UI_14);
		healthAndBlock = new HealthAndBlock(enemy);
		intentContainer = new IntentContainer(enemy.intent());
		modifierLabel = new Text("Modifiers:");
		modifierLabel.setFont(Fonts.UI_14);
		modifierBox = new VBox(modifierLabel);
		modifierBox.setAlignment(Pos.TOP_CENTER);
		updateModifiers();
		sprite = new Sprite(Images.forEnemy(enemy));
		vBox = new VBox(intentContainer, name, sprite, healthAndBlock, modifierLabel, modifierBox);
		vBox.setAlignment(Pos.CENTER);
		sliceLayer = new KnockLayer();
		chipLayer = new KnockLayer();
		getChildren().addAll(vBox, sliceLayer, chipLayer);
		Nodes.setPrefAndMaxWidth(this, 300);
		setOnMouseClicked(me -> mouseClicked());
	}

	private void mouseClicked() {
		if(Vis.handLayer().hasSelected())
			Vis.handLayer().selected().requestStartBeingPlayed(enemy());
	}
	
	public void startHNBTransition(boolean resume) {
		HealthBar hb = healthAndBlock.healthBar();
		BlockIndicator bi = healthAndBlock.blockIndicator();
		boolean hbc = hb.hasChanged(), bic = bi.hasChanged();
		startKnocks(hb, bi);
		if(hbc && !bic) {
			hb.startTransition(resume);
		}
		else if(!hbc && bic) {
			bi.startTransition(resume);
		}
		else if(hbc && bic) {
			//put the resume on whichever takes longer.
			if(hb.secToAnimateChange() > bi.secToAnimateChange()) {
				hb.startTransition(resume);
				bi.startTransition(false);
			}
			else {
				hb.startTransition(false);
				bi.startTransition(true);
			}
		}
		else { //nothing changed
			if(resume)
				Vis.manager().checkedResumeFromAnimation();
		}
	}
	
	public void startBlockTransition(boolean resume, boolean showChip) {
		BlockIndicator bi = healthAndBlock.blockIndicator();
		boolean bic = bi.hasChanged();
		if(showChip)
			startChip(bi);
		if(bic)
			bi.startTransition(resume);
		else if(resume)
			Vis.manager().checkedResumeFromAnimation();
	}
	
	
	private void startKnocks(HealthBar hb, BlockIndicator bi) {
		startChip(bi);
		startSlice(hb);
	}

	private void startChip(BlockIndicator bi) {
		if(bi.change() < 0)
			startChip(-bi.change());
	}
	
	private void startSlice(HealthBar hb) {
		if(hb.hpChange() < 0)
			startSlice(-hb.hpChange());
	}

	private void startSlice(int damage) {
		startKnock(sliceLayer, new Slice(damage));
	}
	
	private void startChip(int damage) {
		startKnock(chipLayer, new Chip(damage));
	}
	
	private void startKnock(KnockLayer layer, NumberKnock knock) {
		Nodes.setLayout(knock, getMaxWidth() * .5 - Slice.WIDTH * .5, getHeight() * .5 - Slice.HEIGHT * .5);
		layer.getChildren().add(knock);
		knock.startAnimation();
	}
	
	public void updateHNBInstantly() {
		healthAndBlock.update();
	}

	public void updateModifiers() {
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
	
	public Enemy enemy() {
		return enemy;
	}
	
}
