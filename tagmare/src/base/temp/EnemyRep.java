package base.temp;

import java.util.*;

import base.VisualManager;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.Health;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import utils.Nodes;

public class EnemyRep extends StackPane {

	private static final double WIDTH = 100, HEIGHT = 100;
	private static final Map<Enemy, EnemyRep> MAP = new HashMap<>();
	
	public static EnemyRep of(Enemy enemy) {
		if(!MAP.containsKey(enemy))
			MAP.put(enemy, new EnemyRep(enemy));
		return MAP.get(enemy);
	}
	
	private final VBox vBox;
	private final Enemy enemy;
	private final Text healthAndBlock, intent;
	
	public EnemyRep(Enemy enemy) {
		setBackground(Backgrounds.of(Color.RED));
		this.enemy = enemy;
		Text name = new Text(enemy.name());
		healthAndBlock = new Text("UPDATE ME PLS");
		intent = new Text("UPDATE ME PLS");
		vBox = new VBox(name, healthAndBlock, intent);
		getChildren().add(vBox);
		Nodes.setMaxSize(this, WIDTH, HEIGHT);
		this.setOnMouseClicked(eh -> mouseClicked());
	}
	
	private void mouseClicked() {
		HandDisplay hd = GameScene.INSTANCE.handDisplay;
		HandCardRep hcr = hd.selected();
		if(hcr == null)
			return;
		Card card = hcr.card();
		if(!card.isTargetted())
			return;
		VisualManager.requestPlayCardFromHand(card, enemy);
	}
	
	public void update() {
		Health health = enemy().health();
		healthAndBlock.setText(String.format("%d/%d (%d block)", health.hp(), health.max(), enemy().block().amount()));
		intent.setText(enemy().intent().toString());
	}
	
	public Enemy enemy() {
		return enemy;
	}
	
}
