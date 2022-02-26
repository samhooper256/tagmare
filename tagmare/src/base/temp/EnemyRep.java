package base.temp;

import java.util.*;

import base.VisualManager;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.cards.Card;
import mechanics.enemies.Enemy;
import utils.Nodes;

public class EnemyRep extends StackPane {

	private static final double WIDTH = 100, HEIGHT = 100;
	private static final Map<Enemy, EnemyRep> MAP = new HashMap<>();
	
	private final VBox vBox;
	
	public static EnemyRep of(Enemy enemy) {
		if(!MAP.containsKey(enemy))
			MAP.put(enemy, new EnemyRep(enemy));
		return MAP.get(enemy);
	}
	
	private final Enemy enemy;
	private final Text health;
	
	public EnemyRep(Enemy enemy) {
		setBackground(Backgrounds.of(Color.RED));
		this.enemy = enemy;
		Text name = new Text(enemy.name());
		health = new Text("UPDATE ME PLS");
		vBox = new VBox(name, health);
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
		health.setText(String.format("%d/%d", enemy().health().hp(), enemy().health().max()));
	}
	
	public Enemy enemy() {
		return enemy;
	}
	
}
