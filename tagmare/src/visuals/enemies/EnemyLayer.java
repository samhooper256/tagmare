package visuals.enemies;

import java.util.List;

import javafx.scene.layout.Pane;
import mechanics.Hub;
import mechanics.enemies.Enemy;
import visuals.fxutils.Nodes;

public class EnemyLayer extends Pane {

	public EnemyLayer() {
		setupEnemies(Hub.combat().enemies());
	}
	
	private void setupEnemies(List<Enemy> enemies) {
		getChildren().clear();
		double x = 400;
		for(Enemy e : enemies) {
			EnemyRepresentation er = EnemyRepresentation.of(e);
			Nodes.setLayout(er, x, 100);
			getChildren().add(er);
			x += 400;
		}
	}
	
}
