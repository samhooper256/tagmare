package base.temp;

import java.util.StringJoiner;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mechanics.enemies.Enemy;
import mechanics.modifiers.*;

public class EModifierDisplay extends VBox {

	private final Text title;
	private final Text list;
	
	private Enemy enemy;
	
	public EModifierDisplay() {
		title = new Text("Modifiers:\n");
		title.setUnderline(true);
		list = new Text();
		enemy = null;
		getChildren().addAll(title, list);
		setBackground(Backgrounds.of(Color.LIME));
		setVisible(false);
	}
	
	public void update(Enemy e) {
		enemy = e;
		ModifierSet mset = e.modifiers();
		if(mset.isEmpty()) {
			list.setText("none");
		}
		else {
			StringJoiner j = new StringJoiner("\n");
			for(Modifier m : mset) {
				j.add(m.toString());
			}
			list.setText(j.toString());
		}
	}
	
	public void update() {
		if(enemy != null)
			update(enemy);
	}
	
}
