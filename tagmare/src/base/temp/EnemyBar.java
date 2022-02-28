package base.temp;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import mechanics.Hub;
import mechanics.enemies.Enemy;

public class EnemyBar extends HBox {

	public EnemyBar() {
		super(8);
		setAlignment(Pos.CENTER);
	}
	
	public void update() {
		getChildren().clear();
		for(Enemy e : Hub.enemies()) {
			EnemyRep rep = EnemyRep.of(e);
			rep.update();
			getChildren().add(rep);
		}
	}
	
}
