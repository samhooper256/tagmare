package visuals.enemies;

import java.util.WeakHashMap;

import base.temp.Backgrounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.enemies.Enemy;
import visuals.fxutils.Nodes;

public class EnemyRepresentation extends StackPane {

	private static final WeakHashMap<Enemy, EnemyRepresentation> MAP = new WeakHashMap<>();
	
	public static EnemyRepresentation of(Enemy enemy) {
		if(!MAP.containsKey(enemy))
			MAP.put(enemy, new EnemyRepresentation(enemy));
		return MAP.get(enemy);
	}
	
	private final Enemy enemy;
	private final Text name;
	
	private EnemyRepresentation(Enemy enemy) {
		this.enemy = enemy;
		name = new Text(enemy.name());
		getChildren().add(name);
		setBackground(Backgrounds.of(Color.PINK));
		Nodes.setPrefAndMaxSize(this, 300, 300);
	}
	
	public Enemy enemy() {
		return enemy;
	}
	
}
